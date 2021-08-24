package com.lister.employeeonboardingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue
    private int addressId;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    private String type;
    @NotNull
    private String flatNumber;
    @NotNull
    private String streetName;
    @NotNull
    private String area;
    @NotNull
    private String country;
    @NotNull
    private String city;
    private String state;
    @NotNull
    private String mapCoordinates;
    @NotNull
    private int pincode;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", type='" + type + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", area='" + area + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", mapCoordinates='" + mapCoordinates + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
