package com.worktrial.task.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "Label cannot be blank!")
    private String label;

    @NotBlank(message = "Description cannot be blank!")
    private String description;

    @NotBlank(message = "Price cannot be blank!")
    private Float price;
}
