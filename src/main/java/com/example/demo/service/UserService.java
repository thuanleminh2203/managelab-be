package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	List<UserDetailDTO> findAll();

	Page<UserDetailDTO> getUsername(Pageable pageable);
}
