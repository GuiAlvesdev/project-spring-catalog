package com.guialvesdev.catalog.repository;



import com.guialvesdev.catalog.entities.Role;
import com.guialvesdev.catalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {



}
