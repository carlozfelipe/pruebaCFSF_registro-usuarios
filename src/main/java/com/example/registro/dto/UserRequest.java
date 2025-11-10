package com.example.registro.dto;

import javax.validation.constraints.NotBlank;


import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    @NotBlank private String name;
    @NotBlank private String email;
    @NotBlank private String password;
    private List<PhoneDto> phones;

}
