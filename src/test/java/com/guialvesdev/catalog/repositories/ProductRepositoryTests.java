package com.guialvesdev.catalog.repositories;


import com.guialvesdev.catalog.entities.Product;
import com.guialvesdev.catalog.repository.ProductRepository;
import com.guialvesdev.catalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    private long exintingfId = 1L;
    private long nonExistingId = 1000L;

    private long countTotalProducts = 25L;
    


    @BeforeEach
    void setUp() throws Exception {
        exintingfId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;


    }


    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdNull(){

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }





    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectIdExists(){



        repository.deleteById(exintingfId);

        Optional<Product> result = repository.findById(exintingfId);
        Assertions.assertFalse(result.isPresent());


    }

    @Test
    public void deleteShouldIdThrowEmptyResultDataAcessExceptionWhenIdNotExist(){


        Assertions.assertThrows(EmptyResultDataAccessException.class,() -> {
            repository.deleteById(nonExistingId);
        });
    }










}
