package com.worktrial.task.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerAddressDto {
    @NotBlank(message = "Street name cannot be blank!")
    private String street;

    @NotBlank(message = "House number cannot be blank!")
    private String houseNumber;

    @NotBlank(message = "State cannot be blank!")
    private String state;

    @NotBlank(message = "Country cannot be blank!")
    private String country;

    @NotBlank(message = "City cannot be blank!")
    private String city;

    @NotBlank(message = "Zip code cannot be blank!")
    private String zipCode;
}
