package com.app.finance.controllers;

import com.app.finance.dtos.UserDTO;
import com.app.finance.entities.Users;
import com.app.finance.repositories.UserRepository;
import com.app.finance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> postUsers(@RequestBody Users users) {
        try{
            userService.saveUser(users);
            return ResponseEntity.ok("User saved successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users users) {

        return userService.login(users);
    }


    @PutMapping("/update/{userId}")
    public List<Users> putUser(@PathVariable Long userId, @RequestBody Users users) {
        Users existingUser = userService.getUserById(userId);

        if(existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if(users.getName() != null) {
            existingUser.setName(users.getName());
        }

        if(users.getLogin() != null) {
            existingUser.setLogin(users.getLogin());
        }

        if(users.getEmail() != null) {
            existingUser.setEmail(users.getEmail());
        }

        if(users.getPassword() != null) {
            existingUser.setPassword(users.getPassword());
        }

        userService.saveUser(existingUser);
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{id}")
    public List<Users> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return userService.getUsers();
    }
}
