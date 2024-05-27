package com.app.finance.dtos;

import com.app.finance.entities.Users;

public record UserDTO(Long id, String name) {
    public UserDTO(Users users){
        this(users.getId(), users.getName());
    }
}
