package com.example.demo.serviceImpl;

import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentRequest;
import com.example.demo.entity.CommentEntity;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ObjectMapper mapper;

    @Override
    public CommentDTO save(CommentRequest rq, UserPrincipal info) {
        CommentEntity commentEntity = mapper.convertValue(rq,CommentEntity.class);
        commentEntity.setFullName(info.getFullName());
        commentEntity.setUsername(info.getUsername());
        commentEntity.setUserId(info.getId());
        return  mapper.convertValue(commentRepository.save(commentEntity),CommentDTO.class);
    }
}
