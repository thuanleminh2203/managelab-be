package com.example.demo.serviceImpl;

import java.util.List;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.repository.UserDetailRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDetailRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDetailDTO> findAll() {
		return repository.getAll();
	}

	@Override
	public Page<UserDetailDTO> getUsername(Pageable pageable) {
		return userRepository.getUsername(pageable);
	}
}
