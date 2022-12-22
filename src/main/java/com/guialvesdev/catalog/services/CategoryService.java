package com.guialvesdev.catalog.services;


import com.guialvesdev.catalog.entities.Category;
import com.guialvesdev.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;



    public List<Category> findAll(){
        return repository.findAll();


    }




}
