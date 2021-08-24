package com.lister.employeeonboardingsystem.voobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteInput {
    @NotNull(message = "Email must be entered")
    @Email(message = "Incorrect email")
    private String email;
    @NotNull(message = "Password must not be null")
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message="Incorrect Password")
    private String password;
    @Positive(message = "Role must not be entered")
    private int role;
    @NotNull(message = "Name must be entered")
    private String name;

}
