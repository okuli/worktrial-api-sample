package com.worktrial.task.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.worktrial.task.enums.CustomerCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CustomerDto {
    @NotBlank(message = "Name cannot be blank!")
    private String lastName;

    @NotBlank(message = "First name cannot be blank!")
    private String firstName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Birthday cannot be blank!")
    private Date birthdate;

    @NotBlank(message = "Job title cannot be blank!")
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be blank!")
    private CustomerCategory category;

    @NotBlank(message = "Email cannot be blank!")
    private String emailAddress;

    @NotNull(message = "Customer address cannot be blank!")
    private CustomerAddressDto customerAddress;

}
