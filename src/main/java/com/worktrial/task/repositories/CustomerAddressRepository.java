package com.worktrial.task.repositories;

import com.worktrial.task.models.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, String> {
}
