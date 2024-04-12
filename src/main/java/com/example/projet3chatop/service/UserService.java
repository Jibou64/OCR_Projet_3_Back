package com.example.projet3chatop.service;

import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor taking UserRepository repository as parameter
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to retrieve a user by their email
    public Optional<User> getName(String name) {
        return this.userRepository.findByEmail(name);
    }

    // Method to delete a user by their ID
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    // Method to find a user by their ID
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
