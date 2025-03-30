package com.marsops.crudapp.repository;

import com.marsops.crudapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending JpaRepository provides CRUD operations without writing implementation.
public interface ProductRepository extends JpaRepository<Product, Long> {
}
