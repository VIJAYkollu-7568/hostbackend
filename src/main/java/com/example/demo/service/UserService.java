package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String signup(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "User already exists";
        }

        userRepository.save(user);
        return "Signup successful";
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }
}