package com.example.registro.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserRequest {
    @NotBlank private String name;
    @NotBlank private String email;
    @NotBlank private String password;
    private List<PhoneDto> phones;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<PhoneDto> getPhones() { return phones; }
    public void setPhones(List<PhoneDto> phones) { this.phones = phones; }
}
