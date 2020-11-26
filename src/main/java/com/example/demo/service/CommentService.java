package com.example.demo.service;

import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentRequest;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
public interface CommentService {
    CommentDTO save(CommentRequest rq, UserPrincipal info);
}
