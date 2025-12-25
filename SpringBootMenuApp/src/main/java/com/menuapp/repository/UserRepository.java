package com.menuapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.menuapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
