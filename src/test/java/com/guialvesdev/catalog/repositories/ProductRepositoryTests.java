package com.guialvesdev.catalog.repositories;


import com.guialvesdev.catalog.entities.Product;
import com.guialvesdev.catalog.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectIdExists(){

        long exintingfId = 1L;

        repository.deleteById(exintingfId);

        Optional<Product> result = repository.findById(exintingfId);
        Assertions.assertFalse(result.isPresent());


    }

    @Test
    public void deleteShouldIdThrowEmptyResultDataAcessExceptionWhenIdNotExist(){
        long nonExistingId = 1000L;

        Assertions.assertThrows(EmptyResultDataAccessException.class,() -> {
            repository.deleteById(nonExistingId);
        });
    }







}
