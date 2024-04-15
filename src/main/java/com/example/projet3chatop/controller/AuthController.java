package com.example.projet3chatop.controller;
import javax.validation.Valid;
import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.mapper.UserMapper;
import com.example.projet3chatop.service.UserService;
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
    private final UserService userService;

    // Constructor injecting necessary dependencies.
    AuthController(AuthenticationManager authenticationManager,
                   PasswordEncoder passwordEncoder,
                   JwtUtils jwtUtils,
                   UserRepository userRepository,
                   UserService userService,
                   UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userService = userService;

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

    @PostMapping("/register")
    public Map<String, String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User registeredUser = userService.registerUser(signUpRequest);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwtUtils.generateJwtToken(registeredUser.getEmail()));

        // Response indicating successful user registration.
        return response;
    }

    // Endpoint to retrieve profile details of the currently logged-in user.
    @GetMapping("/me")
    public UserDto userProfile(@RequestHeader(value="Authorization",required=false) String jwt) {

        User user = userService.findbyEmail(jwtUtils.getUserNameFromJwtToken(jwt.substring(7)));

        if (user == null) {
            return null;
        }

        UserDto userDto = userMapper.toDto(user);

        userDto.setCreated_at(user.getCreated_at());

        return userDto;
    }
}