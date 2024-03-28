package com.example.projet3chatop.service;

import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructeur prenant en paramètre le repository UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour récupérer un utilisateur par son email
    public Optional<User> getName(String name) {
        return this.userRepository.findByEmail(name);
    }

    // Méthode pour supprimer un utilisateur par son ID
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    // Méthode pour trouver un utilisateur par son ID
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
