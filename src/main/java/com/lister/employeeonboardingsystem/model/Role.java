package com.lister.employeeonboardingsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Role {
    @Id
    private int roleId;
    private String roleName;
}
