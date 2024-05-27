package com.app.finance.controllers;

import com.app.finance.dtos.UserDTO;
import com.app.finance.entities.Users;
import com.app.finance.repositories.UserRepository;
import com.app.finance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public List<Users> postUsers(@RequestBody Users users) {
        userService.saveUser(users);
        return userService.getUsers();
    }
}
