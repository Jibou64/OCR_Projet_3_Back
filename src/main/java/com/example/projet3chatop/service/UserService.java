package com.example.projet3chatop.service;

import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.exception.EmailAlreadyUsed;
import com.example.projet3chatop.payload.request.SignupRequest;
import com.example.projet3chatop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    // Constructor taking UserRepository repository as parameter
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User findbyEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    // Method to register a new user
    public User registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyUsed();
        }

        // Creating a new user with encrypted password.
        User user = new User(
                signUpRequest.getEmail(),
                false,
                signUpRequest.getName(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );
        return userRepository.save(user);
    }
}
