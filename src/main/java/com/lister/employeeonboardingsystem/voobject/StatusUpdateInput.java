package com.lister.employeeonboardingsystem.voobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateInput {
    @NotNull
    @Positive
    private int empId;
    @NotNull
    @Size(max = 10)
    private String action;
    private String rejectReason;

}
