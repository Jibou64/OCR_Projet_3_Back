package com.example.projet3chatop.controller;

import javax.validation.Valid;
import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.exception.EmailAlreadyUsed;
import com.example.projet3chatop.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.projet3chatop.payload.request.LoginRequest;
import com.example.projet3chatop.payload.request.SignupRequest;
import com.example.projet3chatop.repository.UserRepository;
import com.example.projet3chatop.security.jwt.JwtUtils;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Constructor injecting necessary dependencies.
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

    // Endpoint for user authentication.
    @PostMapping("/login")
    public Map<String, String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String,String> map=new HashMap<>();
        map.put("token", jwtUtils.generateJwtToken((loginRequest.getEmail())));

        return map;
    }

    // Endpoint for registering a new user.
    @PostMapping("/register")
    public Map<String, String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
        userRepository.save(user);

        Map<String,String> map=new HashMap<>();
        map.put("token", jwtUtils.generateJwtToken(signUpRequest.getEmail()));

        // Response indicating successful user registration.
        return  map;
    }

    // Endpoint to retrieve profile details of the currently logged-in user.
    @GetMapping("/me")
    public UserDto userProfile(@RequestHeader(value="Authorization",required=false) String jwt) {
        // Retrieving username from JWT token and converting to DTO.
        return userMapper.toDto(userRepository.findByEmail(jwtUtils.getUserNameFromJwtToken(jwt.substring(7))).get());
    }
}