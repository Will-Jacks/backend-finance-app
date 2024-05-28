package com.app.finance.repositories;

import com.app.finance.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByLogin(String login);
    Optional<Users> findUserByEmail(String email);
}
