package com.menuapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menuapp.entity.User;
import com.menuapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void signup(User user) {
        repo.save(user);
    }

    public Optional<User> login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
