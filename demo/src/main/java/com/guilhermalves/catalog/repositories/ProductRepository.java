package com.guilhermalves.catalog.repositories;

import com.guilhermalves.catalog.entities.Category;
import com.guilhermalves.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {






}
