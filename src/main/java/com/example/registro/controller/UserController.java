package com.example.registro.controller;

import com.example.registro.dto.UserRequest;
import com.example.registro.dto.UserResponse;
import com.example.registro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(name = "Usuarios", description = "Operaciones relacionadas con el registro de usuarios")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) { this.userService = userService; }

    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario validando formato de email y contraseña.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                            content = @Content(schema = @Schema(example = "{mensaje : errorValidación")))
            })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        UserResponse resp = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
