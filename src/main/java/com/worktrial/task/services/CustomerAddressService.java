package com.worktrial.task.services;

import com.worktrial.task.dtos.CustomerAddressDto;
import com.worktrial.task.models.CustomerAddress;

public interface CustomerAddressService {
    CustomerAddress createAddress(CustomerAddressDto customerAddressDto);
}
