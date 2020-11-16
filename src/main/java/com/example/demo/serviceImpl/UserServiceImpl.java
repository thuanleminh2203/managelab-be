package com.example.demo.serviceImpl;

import java.util.List;

import com.example.demo.dto.UserChatDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserSearchDTO;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<UserChatDTO> findAll(int id) {
        return mapper.convertValue(userRepository.findAll(id), new TypeReference<>() {
        });
    }

    @Override
    public Page<UserDetailDTO> getUsername(Pageable pageable) {
        return userRepository.getUsername(pageable);
    }

    @Override
    public List<UserSearchDTO> getByName(String name) {
        return mapper.convertValue(userRepository.getByName(name), new TypeReference<>() {
        });
//		return repository.getByName(name);
    }
}
