package com.guilhermalves.catalog.repositories;


import com.guilhermalves.catalog.entities.Product;
import com.guilhermalves.catalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long noExistingId;
    private long countTotalProducts;



    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        noExistingId = 200L;
        countTotalProducts = 25l;
    }



    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());


    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull(){

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());


    }



    @Test
    public void deleteShouldIdThrowEmptyDataAccessExceptionWhenIdDoesExist(){

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(noExistingId);

        });

        


    }














}
