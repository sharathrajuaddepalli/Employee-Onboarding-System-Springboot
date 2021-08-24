package com.lister.employeeonboardingsystem.voobject;


import com.lister.employeeonboardingsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsvo {
    @NotNull
    private int empId;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String bloodGroup;
    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String aadharNumber;
    private LocalDate dob;
    @NotNull
    private String gender;
    @NotNull
    private double sslc;
    @NotNull
    private double hsc;
    @NotNull
    private double ug;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    private String fatherName;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    private String motherName;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    private String emergencyContactName;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    private String emergencyContactRelation;
    @NotNull
    private String emergencyContactNumber;
    @NotNull
    private List<Address> addressList;

    private String action;

}
