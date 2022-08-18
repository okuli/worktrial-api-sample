package com.worktrial.task.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    @NotBlank(message = "CustomerId can not be blank!")
    private String customerId;
    @NotEmpty(message = "At least 1 product must exist!")
    private List<String> productIds;
    @NotBlank(message = "Additional info cannot be blank!")
    private String additionalInfo;
}
