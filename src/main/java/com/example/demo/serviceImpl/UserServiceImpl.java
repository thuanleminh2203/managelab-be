package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserDetailRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDetailRepository repository;

	public List<UserDetails> findAll() {
		return repository.findAll();
	}
}