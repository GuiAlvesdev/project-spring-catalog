package com.guialvesdev.catalog.repository;


import com.guialvesdev.catalog.entities.Category;
import com.guialvesdev.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
