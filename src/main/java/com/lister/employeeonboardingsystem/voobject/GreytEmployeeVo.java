package com.lister.employeeonboardingsystem.voobject;

import com.lister.employeeonboardingsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GreytEmployeeVo {
    private int empId;

    private String code;

    private String role;

    private String name;

    private String phoneNumber;

    private String emailId;

    private String bloodGroup;

    private String aadharNumber;
    private LocalDate dob;

    private String gender;
    private double sslc;
    private double hsc;
    private double ug;

    private String fatherName;

    private String motherName;

    private String emergencyContactName;

    private String emergencyContactRelation;

    private String emergencyContactNumber;

    private List<Address> addressList;
}
