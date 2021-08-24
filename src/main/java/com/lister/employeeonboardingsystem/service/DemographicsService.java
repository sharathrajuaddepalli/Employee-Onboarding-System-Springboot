package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.enums.StatusDescription;
import com.lister.employeeonboardingsystem.exception.GreytbStoreException;
import com.lister.employeeonboardingsystem.exception.InvalidActionException;
import com.lister.employeeonboardingsystem.voobject.EmployeeDetailsvo;
import com.lister.employeeonboardingsystem.exception.DetailsNotFoundException;
import com.lister.employeeonboardingsystem.exception.NoRecordsFoundException;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
import com.lister.employeeonboardingsystem.repository.DemographicsRepository;
import com.lister.employeeonboardingsystem.voobject.GreytEmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@Slf4j     //annotation used for logging
@Service
public class DemographicsService {
    private final DemographicsRepository demographicsRepository;

    private final EmailService emailService;
    private final RoleService roleService;

    public DemographicsService(DemographicsRepository demographicsRepository, EmailService emailService, RoleService roleService) {
        this.demographicsRepository = demographicsRepository;
        this.emailService = emailService;
        this.roleService = roleService;
    }
    //method that saves the details(demographics) of the employee

    /**
     * @param demographics
     * @param uuid
     * @return
     */
    public Demographics saveDemographics(Demographics demographics,UUID uuid) {
        log.info("save Demographics called successfully when saving with uuid"+uuid.toString());
        return demographicsRepository.save(demographics);
    }

    /**
     * @return
     */
    //method that retrieves the data of all employees
    public List<Demographics> getEmployees() {
        log.info("get Employees function called successfully when trying to retrieve all employees");
        return demographicsRepository.findAll();
    }

    /**
     * @param demographicInfo
     * @param uuid
     * @return
     * @throws InvalidActionException
     */
    //method that is called when hr performs certain action on the filled employee details in the table
    @Transactional(rollbackFor = GreytbStoreException.class)
    public boolean updateStatus(StatusUpdateInput demographicInfo,UUID uuid) throws InvalidActionException{
        log.info("update Status function called successfully of {}",demographicInfo.getAction() + "employee with id" + demographicInfo.getEmpId()+"with uuid"+uuid);
        //getting the details of the employee to update it
        Demographics realDemographics = getEmployeeDetails(demographicInfo.getEmpId(),uuid);
        String s = "Please fill the form as quickly as possible";
        String action = demographicInfo.getAction();
        switch (action) {
            case "approve":
                //using the rest template to update the information in the greyt database
                RestTemplate restTemplate = new RestTemplate();
                String resourceUrl ="http://localhost:9092/listeremployee";
                GreytEmployeeVo greytEmployeeVo = sendAsGreytEmployee(realDemographics);
                HttpEntity<GreytEmployeeVo> request = new HttpEntity<>(greytEmployeeVo);
                greytEmployeeVo = restTemplate.postForObject(resourceUrl,request,GreytEmployeeVo.class);
                if(greytEmployeeVo == null)
                    throw new GreytbStoreException(demographicInfo);
                log.info("Employee stored in greyt database"+greytEmployeeVo+"with uuid"+uuid);
                realDemographics.setStatus(StatusDescription.Status.ACCEPT.getStatusName());
                break;
            case "reject":
                realDemographics.setStatus(StatusDescription.Status.REJECT.getStatusName());
                realDemographics.setRejectReason(demographicInfo.getRejectReason());
                emailService.sendMail(realDemographics.getEmailId(), "Your submitted form was rejected due to following", demographicInfo.getRejectReason(),uuid);
                break;
            case "notify":
                emailService.sendMail(realDemographics.getEmailId(), "Notification to fill the form or update the form", s,uuid);
                break;
            default:
                log.error("invalid action sent : " + action+"with uuid"+uuid.toString());
                throw new InvalidActionException();
        }
        demographicsRepository.save(realDemographics);
        return true;
    }

    /**
     * @return
     */
    //this method retrieves the maximum value of the code and increment that to give the code
    public String generateCode() {
        log.info("trying to generate the code");
        String maxCodeValue = demographicsRepository.findMaxCode();
        if (maxCodeValue == null)
            return "LIS2400";
        int code = Integer.parseInt(maxCodeValue.substring(3, 7)) + 1;
        return "LIS" + code;
    }

    /**
     * @param employeeDetailsVo
     * @param uuid
     * @return
     */
    //method that gets called when user submit or save the form
    public boolean update(EmployeeDetailsvo employeeDetailsVo, UUID uuid) {
        log.info("saving the employee details with uuid{}",uuid.toString());
        Demographics realDemographics = getEmployeeDetails(employeeDetailsVo.getEmpId(),uuid);
        realDemographics.setName(employeeDetailsVo.getName());
        realDemographics.setPhoneNumber(employeeDetailsVo.getPhoneNumber());
        realDemographics.setBloodGroup(employeeDetailsVo.getBloodGroup());
        realDemographics.setAadharNumber(employeeDetailsVo.getAadharNumber());
        realDemographics.setDob(employeeDetailsVo.getDob());
        realDemographics.setGender(employeeDetailsVo.getGender());
        realDemographics.setSslc(employeeDetailsVo.getSslc());
        realDemographics.setHsc(employeeDetailsVo.getHsc());
        realDemographics.setUg(employeeDetailsVo.getUg());
        realDemographics.setFatherName(employeeDetailsVo.getFatherName());
        realDemographics.setMotherName(employeeDetailsVo.getMotherName());
        realDemographics.setEmergencyContactName(employeeDetailsVo.getEmergencyContactName());
        realDemographics.setEmergencyContactRelation(employeeDetailsVo.getEmergencyContactRelation());
        realDemographics.setEmergencyContactNumber(employeeDetailsVo.getEmergencyContactNumber());
        if (employeeDetailsVo.getAction() != null && employeeDetailsVo.getAction().equals("submit")) {
            realDemographics.setStatus(StatusDescription.Status.SUBMIT.getStatusName());
        }
        realDemographics.setAddressList(employeeDetailsVo.getAddressList());
        demographicsRepository.save(realDemographics);
        return true;
    }

    /**
     * @param uuid
     * @return
     */
    public List<Demographics> getAllEmployees(UUID uuid) {
        log.info("tried to retrieve all employee details with uuid"+uuid.toString());
        List<Demographics> l;
        try {
            l = demographicsRepository.findAll();
        } catch (NoRecordsFoundException exception) {
            log.error("error occured when trying to get all employees with uuid"+uuid.toString());
            throw new NoRecordsFoundException();
        }
        return l;
    }

    /**
     * @param empId
     * @param uuid
     * @return
     */
    //method that gets called when existing employee gets called
    public Demographics getEmployeeDetails(int empId,UUID uuid) {
        log.info("tried to retrieve employee details with id" + empId+"with uuid"+uuid.toString());
        Demographics d = demographicsRepository.findByEmpId(empId);
        if (d == null)
            throw new DetailsNotFoundException(empId);
        return d;
    }

    /**
     * @param empId
     * @param uuid
     * @return
     */
    //methhod that gets called when hr clicks on notify button
    public boolean notify(int empId,UUID uuid) {
        log.info("notifying the employee");
        String to = (demographicsRepository.findByEmpId(empId)).getEmailId();
        emailService.sendMail(to,"Notification to fill the form or update the form","Please fill the form as quickly as possible",uuid);
        return true;
    }

    /**
     * @param demographics
     * @return
     */
    //method that formats the object in such a way to send it to greyt database.
    public GreytEmployeeVo sendAsGreytEmployee(Demographics demographics){
        GreytEmployeeVo greytEmployeeVo = new GreytEmployeeVo();
        greytEmployeeVo.setEmpId(demographics.getEmpId());
        greytEmployeeVo.setCode(demographics.getCode());
        greytEmployeeVo.setRole(roleService.findRoleNameById(demographics.getRoleId()));
        greytEmployeeVo.setName(demographics.getName());
        greytEmployeeVo.setPhoneNumber(demographics.getPhoneNumber());
        greytEmployeeVo.setEmailId(demographics.getEmailId());
        greytEmployeeVo.setAadharNumber(demographics.getAadharNumber());
        greytEmployeeVo.setBloodGroup(demographics.getBloodGroup());
        greytEmployeeVo.setDob(demographics.getDob());
        greytEmployeeVo.setGender(demographics.getGender());
        greytEmployeeVo.setSslc(demographics.getSslc());
        greytEmployeeVo.setHsc(demographics.getHsc());
        greytEmployeeVo.setUg(demographics.getUg());
        greytEmployeeVo.setFatherName(demographics.getFatherName());
        greytEmployeeVo.setMotherName(demographics.getMotherName());
        greytEmployeeVo.setEmergencyContactName(demographics.getEmergencyContactName());
        greytEmployeeVo.setEmergencyContactNumber(demographics.getEmergencyContactNumber());
        greytEmployeeVo.setEmergencyContactRelation(demographics.getEmergencyContactRelation());
        greytEmployeeVo.setAddressList(demographics.getAddressList());
        return greytEmployeeVo;
    }
}
