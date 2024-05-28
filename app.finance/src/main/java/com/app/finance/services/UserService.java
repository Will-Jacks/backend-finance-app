package com.app.finance.services;

import com.app.finance.entities.Users;
import com.app.finance.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void saveUser(Users user) {
        if(userRepository.findUserByLogin(user.getLogin()).isPresent()){
            throw new IllegalArgumentException("Login already exists");
        }
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email has already been registered");
        }
        userRepository.save(user);
    }

    public ResponseEntity<String> login(Users user) {
        Optional<Users> existingUser = userRepository.findUserByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            if (existingUser.get().getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Usuário está registrado e autenticado");
            } else {
                return ResponseEntity.status(401).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
