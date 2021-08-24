package com.lister.employeeonboardingsystem.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Demographics {

    @Id
    @GeneratedValue
    private int empId;
    @Column(length = 10, unique = true)
    private String code;
    @Column(nullable = false)
    private int roleId;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 15, unique = true)
    private String phoneNumber;
    @Email(message = "Incorrect Email")
    @Column(length = 50, nullable = false, unique = true)
    private String emailId;
    @Column(length = 5)
    private String bloodGroup;
    @Column(length = 12)
    private String aadharNumber;
    private LocalDate dob;
    @Column(length = 6)
    private String gender;
    private double sslc;
    private double hsc;
    private double ug;
    @Column(length = 30)
    private String fatherName;
    @Column(length = 30)
    private String motherName;
    @Column(length = 30)
    private String emergencyContactName;
    @Column(length = 30)
    private String emergencyContactRelation;
    @Column(length = 15)
    private String emergencyContactNumber;
    @NotNull
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Incorrect Password")
    @Column(length = 20, nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(length = 10)
    private String status;
    @Column(length = 100)
    private String rejectReason;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_Id")
    private List<Address> addressList;

    @Override
    public String toString() {
        return "Demographics{" +
                "empId=" + empId +
                ", code='" + code + '\'' +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", sslc=" + sslc +
                ", hsc=" + hsc +
                ", ug=" + ug +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", emergencyContactName='" + emergencyContactName + '\'' +
                ", emergencyContactRelation='" + emergencyContactRelation + '\'' +
                ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}
