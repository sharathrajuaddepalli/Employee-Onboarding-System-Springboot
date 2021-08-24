package com.lister.employeeonboardingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private int sno;
    @Column(length = 50, nullable = false)
    @Email(message = "Incorrect Email")
    private String emailId;
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Incorrect Password")
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 50)
    private String name;
    @Column(nullable = false)
    private int roleId;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Demographics demographics;

}
