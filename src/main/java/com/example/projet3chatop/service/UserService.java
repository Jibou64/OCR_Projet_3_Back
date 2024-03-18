package com.example.projet3chatop.service;

import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    public Optional<User> getName(String name) {

        return this.userRepository.findByEmail(name);

    }
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
