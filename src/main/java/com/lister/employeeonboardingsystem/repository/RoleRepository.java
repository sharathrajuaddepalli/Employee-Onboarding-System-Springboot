package com.lister.employeeonboardingsystem.repository;

import com.lister.employeeonboardingsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleId(int roleId);
}
