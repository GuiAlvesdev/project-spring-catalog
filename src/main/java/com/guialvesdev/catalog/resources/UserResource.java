package com.guialvesdev.catalog.resources;


import com.guialvesdev.catalog.dto.ProductDTO;
import com.guialvesdev.catalog.dto.UserDTO;
import com.guialvesdev.catalog.dto.UserInsertDTO;
import com.guialvesdev.catalog.dto.UserUpdateDTO;
import com.guialvesdev.catalog.entities.User;
import com.guialvesdev.catalog.services.ProductService;
import com.guialvesdev.catalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO dto  = userService.FindById(id);
        return ResponseEntity.ok().body(dto);


    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserInsertDTO dto){
        UserDTO newDto = userService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}").buildAndExpand(newDto.getId()).toUri();


        return ResponseEntity.created(uri).body(newDto);


    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        UserDTO newdto = userService.update(id, dto);

        return ResponseEntity.ok().body(newdto);


    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();


    }














}
