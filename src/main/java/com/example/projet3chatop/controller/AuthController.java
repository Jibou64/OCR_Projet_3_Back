package com.example.projet3chatop.controller;

import javax.validation.Valid;

import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.projet3chatop.payload.request.LoginRequest;
import com.example.projet3chatop.payload.request.SignupRequest;
import com.example.projet3chatop.payload.response.JwtResponse;
import com.example.projet3chatop.payload.response.MessageResponse;
import com.example.projet3chatop.repository.UserRepository;
import com.example.projet3chatop.security.jwt.JwtUtils;
import com.example.projet3chatop.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Constructeur prenant les dépendances nécessaires.
    AuthController(AuthenticationManager authenticationManager,
                   PasswordEncoder passwordEncoder,
                   JwtUtils jwtUtils,
                   UserRepository userRepository,
                   UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Endpoint pour l'authentification d'un utilisateur.
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Réponse contenant le token JWT et les informations de l'utilisateur.
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName()));
    }

    // Endpoint pour l'inscription d'un nouvel utilisateur.
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Création d'un nouvel utilisateur avec le mot de passe crypté.
        User user = new User(
                signUpRequest.getEmail(),
                false,
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName()
        );

        userRepository.save(user);

        // Réponse indiquant que l'utilisateur a été enregistré avec succès.
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    // Endpoint pour récupérer les détails du profil de l'utilisateur actuellement connecté.
    @GetMapping("/me")
    public UserDto userProfile(@RequestHeader(value="Authorization",required=false) String jwt) {
        // Récupération du nom d'utilisateur à partir du token JWT et conversion en DTO.
        return userMapper.toDto(userRepository.findByEmail(jwtUtils.getUserNameFromJwtToken(jwt.substring(7))).get());
    }
}