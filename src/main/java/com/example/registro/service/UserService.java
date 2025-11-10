package com.example.registro.service;

import com.example.registro.dto.*;
import com.example.registro.entity.*;
import com.example.registro.exception.EmailAlreadyExistsException;
import com.example.registro.repository.UserRepository;
import com.example.registro.util.TokenGenerator;
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

        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setToken(TokenGenerator.generate());
        user.setLastLogin(LocalDateTime.now());

        if (request.getPhones() != null) {
            for (PhoneDto p : request.getPhones()) {
                PhoneEntity pe = new PhoneEntity();
                pe.setNumber(p.getNumber());
                pe.setCitycode(p.getCitycode());
                pe.setContrycode(p.getContrycode());
                pe.setUser(user);
                user.getPhones().add(pe);
            }
        }

        UserEntity saved = userRepository.save(user);
        UserResponse resp = new UserResponse();
        resp.setId(saved.getId());
        resp.setCreated(saved.getCreated());
        resp.setModified(saved.getModified());
        resp.setLast_login(saved.getLastLogin());
        resp.setToken(saved.getToken());
        resp.setIsactive(saved.isActive());
        resp.setName(saved.getName());
        resp.setEmail(saved.getEmail());
        resp.setPhones(saved.getPhones().stream().map(ph -> {
            PhoneDto dto = new PhoneDto();
            dto.setNumber(ph.getNumber());
            dto.setCitycode(ph.getCitycode());
            dto.setContrycode(ph.getContrycode());
            return dto;
        }).collect(Collectors.toList()));

        return resp;
    }
}
