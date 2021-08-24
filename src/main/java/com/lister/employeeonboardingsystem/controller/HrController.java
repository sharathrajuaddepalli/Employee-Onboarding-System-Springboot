package com.lister.employeeonboardingsystem.controller;

import com.fasterxml.uuid.Generators;
import com.lister.employeeonboardingsystem.model.Role;
import com.lister.employeeonboardingsystem.service.RoleService;
import com.lister.employeeonboardingsystem.voobject.InviteInput;
import com.lister.employeeonboardingsystem.voobject.StatusUpdateInput;
import com.lister.employeeonboardingsystem.model.Demographics;
import com.lister.employeeonboardingsystem.service.DemographicsService;
import com.lister.employeeonboardingsystem.service.Invite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/hr")
@Slf4j
public class HrController {

    private final DemographicsService demographicsService;

    private final Invite invite;

    private final RoleService roleService;

    public HrController(DemographicsService demographicsService, Invite invite, RoleService roleService) {
        this.demographicsService = demographicsService;
        this.invite = invite;
        this.roleService = roleService;
    }

    /**
     * @param inviteInput
     * @return
     */
    @CrossOrigin
    @PostMapping("/employee")
    public ResponseEntity<Object> addLoginInfo(@Valid @RequestBody InviteInput inviteInput) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("Inviting the employee" + inviteInput.getEmail()+"with uuid"+uuid.toString());
        return ResponseEntity.ok(invite.saveInfo(inviteInput,uuid));
    }

    /**
     * @return
     */
    @CrossOrigin
    @GetMapping("/employees")
    public ResponseEntity<List<Demographics>> getEmployees() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("Retrieving all employees with uuid"+uuid.toString());
        return ResponseEntity.ok(demographicsService.getAllEmployees(uuid));
    }

    /**
     * @param statusUpdateInput
     * @return
     */
    @CrossOrigin
    @PutMapping("/action")
    public ResponseEntity<Object> updateStatus(@Valid @RequestBody StatusUpdateInput statusUpdateInput) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("Updating the status with uuid" + uuid.toString());
        return ResponseEntity.ok(demographicsService.updateStatus(statusUpdateInput,uuid));
    }

    /**
     * @return
     */
    @CrossOrigin
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("retrieving all roles with uuid"+uuid);
        return ResponseEntity.ok(roleService.getAllRoles(uuid));
    }

    /**
     * @param empId
     * @return
     */
    @CrossOrigin
    @PostMapping("/notification/{empId}")
    public ResponseEntity<Object> notifyEmployee(@PathVariable int empId) {
        UUID uuid = Generators.timeBasedGenerator().generate();
        log.info("notifying the employee with uuid"+uuid.toString());
        return ResponseEntity.ok(demographicsService.notify(empId,uuid));
    }

}
