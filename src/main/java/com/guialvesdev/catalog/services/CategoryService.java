package com.guialvesdev.catalog.services;


import com.guialvesdev.catalog.dto.CategoryDTO;
import com.guialvesdev.catalog.entities.Category;
import com.guialvesdev.catalog.repository.CategoryRepository;
import com.guialvesdev.catalog.services.exceptions.DatabaseException;
import com.guialvesdev.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;



    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable){
        Page<Category> list = repository.findAll(pageable);
        return list.map(x -> new CategoryDTO(x));




    }

    @Transactional(readOnly = true)
    public CategoryDTO FindById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada"));
        return new CategoryDTO(entity);




    }

    @Transactional(readOnly = true)
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);

    }


    @Transactional(readOnly = true)
    public CategoryDTO update(Long id , CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("id nao existe" + id);
        }


    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("id nao existe" + id);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violacao de integridade");
        }

    }


}
