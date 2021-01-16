package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.UserChatDTO;
import com.example.demo.dto.UserDetailDTO;
import com.example.demo.dto.UserSearchDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	List<UserChatDTO> findAll(int id);

	Page<UserDetailDTO> getUsername(Pageable pageable);

	List<UserSearchDTO> getByName(String name);

	Optional<User> getInfo(int id);

	void updateAvatar(FileDTO fileDTO, HttpServletRequest request, int id) throws IOException;
	void updateAvatarUser(int id, String url);

}
