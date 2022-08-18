package com.worktrial.task.dtos;

import com.worktrial.task.models.Customer;
import com.worktrial.task.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String id;

    private String additionalInfo;

    private Customer customer;

    private List<Product> products;
}
