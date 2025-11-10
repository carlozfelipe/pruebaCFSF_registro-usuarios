package com.example.registro.util;

import com.example.registro.dto.UserResponse;
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
}
