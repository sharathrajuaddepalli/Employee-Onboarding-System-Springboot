package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.uuid.Generators;
import com.lister.employeeonboardingsystem.voobject.EmployeeDetailsvo;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.service.DemographicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final DemographicsService demographicsService;

    public EmployeeController(DemographicsService demographicsService) {
        this.demographicsService = demographicsService;
    }

    /**
     * @param employeeDetailsVo
     * @return
     */
    @CrossOrigin
    @PutMapping("/details")
    public ResponseEntity<Object> saveEmployeeDetails(@Valid @RequestBody EmployeeDetailsvo employeeDetailsVo) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("saving the employee details" + employeeDetailsVo.getEmpId()+"with uuid"+uuid.toString());
        return ResponseEntity.ok(demographicsService.update(employeeDetailsVo,uuid));
    }

    /**
     * @param empId
     * @return
     */
    @CrossOrigin
    @GetMapping("/{empId}")
    public ResponseEntity<Demographics> getEmployeeDetails(@PathVariable("empId") @Positive(message = "Employee Id should be a positive number") int empId) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("getting the employee details with id" + empId+"with uuid"+uuid.toString());
        return ResponseEntity.ok(demographicsService.getEmployeeDetails(empId,uuid));

    }
}
