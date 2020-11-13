package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDetailDTO;
import com.example.demo.dto.UserSearchDTO;
import com.example.demo.entity.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	List<UserDetailDTO> findAll();

	Page<UserDetailDTO> getUsername(Pageable pageable);

	List<UserSearchDTO> getByName(String name);
}
