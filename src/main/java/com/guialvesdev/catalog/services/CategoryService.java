package com.guialvesdev.catalog.services;


import com.guialvesdev.catalog.dto.CategoryDTO;
import com.guialvesdev.catalog.entities.Category;
import com.guialvesdev.catalog.repository.CategoryRepository;
import com.guialvesdev.catalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;



    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());




    }

    @Transactional(readOnly = true)
    public CategoryDTO FindById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new CategoryDTO(entity);




    }
}
