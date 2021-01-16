package com.example.demo.serviceImpl;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.UserChatDTO;
import com.example.demo.dto.UserSearchDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
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

    @Override
    public Optional<User> getInfo(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateAvatar(FileDTO fileDTO, HttpServletRequest request, int id) throws IOException {
        for (MultipartFile fileData : fileDTO.getAvatar()) {
            String name = fileData.getOriginalFilename();
//            String name = "avatar"+id+new Date().getTime();
            String path = System.getProperty("user.dir");
//			System.out.println("======" + path + "/upload" );
            File uploadRootDir = new File(path);
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }
            File serverFile = null;

            if (name != null && name.length() > 0) {
                serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());
                stream.close();
                System.out.println("===name===" + name);

//                serverFile.getName();
//                FileSystemResource file = new FileSystemResource(new File(uploadRootDir.getAbsolutePath() + File.separator + name));
            }
//            String pathAvatar = serverFile.getAbsolutePath();
            updateAvatarUser(id,serverFile.getAbsolutePath());
//            System.out.println("===name===" + name);
        }
    }

    @Override
    public void updateAvatarUser(int id, String url) {
        userRepository.updateAvatarUser(id,url);
    }
}
