package com.worktrial.task.repositories;

import com.worktrial.task.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
