package com.example.registro.util;

import java.time.LocalDateTime;

import com.example.registro.dto.PhoneDto;
import com.example.registro.dto.UserRequest;
import com.example.registro.dto.UserResponse;
import com.example.registro.entity.PhoneEntity;
import com.example.registro.entity.UserEntity;

public class UserMapper {

	public static UserResponse entityToResponse(UserEntity saved) {
		UserResponse resp = new UserResponse();
        resp.setId(saved.getId());
        resp.setCreated(saved.getCreated());
        resp.setModified(saved.getModified());
        resp.setLast_login(saved.getLastLogin());
        resp.setToken(saved.getToken());
        resp.setIsactive(saved.isActive());
		return resp;
	}

	public static UserEntity requestToEntity(UserRequest request) {
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
			return user;
	}
}
