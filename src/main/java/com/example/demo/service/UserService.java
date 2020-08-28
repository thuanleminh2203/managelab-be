package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDetailDTO;

public interface UserService {
	public List<UserDetailDTO> findAll();
}
