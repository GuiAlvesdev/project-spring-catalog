package com.guilhermalves.catalog.services;


import com.guilhermalves.catalog.dto.CategoryDTO;
import com.guilhermalves.catalog.dto.ProductDTO;
import com.guilhermalves.catalog.entities.Category;
import com.guilhermalves.catalog.entities.Product;
import com.guilhermalves.catalog.repositories.CategoryRepository;
import com.guilhermalves.catalog.repositories.ProductRepository;
import com.guilhermalves.catalog.services.exceptions.DatabaseException;
import com.guilhermalves.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x));


    }


    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade nao Localizada"));
        return new ProductDTO(entity, entity.getCategories());




    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);


    }





    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(" Id nao encontrado" +id);

        }
    }


    public void delete(Long id) {
        try{
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("id not found" +id);

        }catch (DataIntegrityViolationException e ){
            throw new DatabaseException("Violacao de Integridade");

        }

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity)
    {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories())
        {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategories().add(category);

        }



    }






}
