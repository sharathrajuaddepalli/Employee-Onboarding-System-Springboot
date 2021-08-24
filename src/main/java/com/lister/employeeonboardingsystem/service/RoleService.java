package com.lister.employeeonboardingsystem.service;

import com.lister.employeeonboardingsystem.model.Role;
import com.lister.employeeonboardingsystem.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param roleId
     * @return
     */
    //method that finds the role name with role id.
    public String findRoleNameById(int roleId) {
        return roleRepository.findByRoleId(roleId).getRoleName();
    }

    /**
     * @param uuid
     * @return
     */
    //method that retrieves all the roles in the database.
    public List<Role> getAllRoles(UUID uuid) {
        log.info("trying to retrieve roles with service"+uuid);
        return roleRepository.findAll();
    }
}
