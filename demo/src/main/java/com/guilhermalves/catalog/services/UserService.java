package com.guilhermalves.catalog.services;


import com.guilhermalves.catalog.dto.*;
import com.guilhermalves.catalog.entities.Category;
import com.guilhermalves.catalog.entities.Product;
import com.guilhermalves.catalog.entities.Role;
import com.guilhermalves.catalog.entities.User;
import com.guilhermalves.catalog.repositories.CategoryRepository;
import com.guilhermalves.catalog.repositories.ProductRepository;
import com.guilhermalves.catalog.repositories.RoleRepository;
import com.guilhermalves.catalog.repositories.UserRepository;
import com.guilhermalves.catalog.services.exceptions.DatabaseException;
import com.guilhermalves.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;






    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));


    }


    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade nao Localizada"));
        return new UserDTO(entity);




    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);


    }





    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);

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

    private void copyDtoToEntity(UserDTO dto, User entity)
    {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());


        entity.getRoles().clear();
        for (RoleDTO roleDto : dto.getRole())
        {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);

        }



    }






}