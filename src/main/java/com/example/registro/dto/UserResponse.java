package com.example.registro.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponse {
    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    private boolean isactive;
    private String name;
    private String email;
    private List<PhoneDto> phones;

    // getters y setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public LocalDateTime getLast_login() {
		return last_login;
	}
	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PhoneDto> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}
    
}
