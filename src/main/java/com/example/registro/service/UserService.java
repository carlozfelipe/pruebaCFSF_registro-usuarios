package com.example.registro.service;

import com.example.registro.dto.*;
import com.example.registro.entity.*;
import com.example.registro.exception.EmailAlreadyExistsException;
import com.example.registro.repository.UserRepository;
import com.example.registro.util.TokenGenerator;
import com.example.registro.util.UserMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String emailRegex;
    private final String passwordRegex;

    public UserService(UserRepository userRepository,
                       @Value("${app.regex.email}") String emailRegex,
                       @Value("${app.regex.password}") String passwordRegex) {
        this.userRepository = userRepository;
        this.emailRegex = emailRegex;
        this.passwordRegex = passwordRegex;
    }

    @Transactional
    public UserResponse register(UserRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> { throw new EmailAlreadyExistsException("El correo ya registrado"); });

        if (!request.getEmail().matches(emailRegex)) throw new IllegalArgumentException("Formato de correo inválido");
        if (!request.getPassword().matches(passwordRegex)) throw new IllegalArgumentException("Formato de contraseña inválido");

        UserEntity user = UserMapper.requestToEntity(request);

        UserEntity saved = userRepository.save(user);
        
        return UserMapper.entityToResponse(saved);
    }

}
