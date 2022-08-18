package com.worktrial.task.services.impl;

import com.worktrial.task.dtos.CustomerAddressDto;
import com.worktrial.task.models.CustomerAddress;
import com.worktrial.task.repositories.CustomerAddressRepository;
import com.worktrial.task.services.CustomerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerAddressServiceImpl implements CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;

    @Override
    public CustomerAddress createAddress(CustomerAddressDto customerAddressDto) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setStreet(customerAddressDto.getStreet());
        customerAddress.setHouseNumber(customerAddressDto.getHouseNumber());
        customerAddress.setState(customerAddressDto.getState());
        customerAddress.setCountry(customerAddressDto.getCountry());
        customerAddress.setCity(customerAddressDto.getCity());
        customerAddress.setZipCode(customerAddressDto.getZipCode());
        return customerAddressRepository.save(customerAddress);
    }
}
