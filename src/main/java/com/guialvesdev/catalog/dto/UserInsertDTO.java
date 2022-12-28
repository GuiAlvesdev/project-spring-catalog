package com.guialvesdev.catalog.dto;

import com.guialvesdev.catalog.services.validation.UserInsertValid;

import java.io.Serializable;


@UserInsertValid
public class UserInsertDTO extends UserDTO  {
    private static final long serialVersionUID = 1L;


    private String password;


    UserInsertDTO(){
        super();

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
