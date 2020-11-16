package com.example.demo.service;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MessageResponseDTO;

import java.util.List;

public interface MessageService {

    MessageDTO save(MessageDTO messageDTO);

    List<MessageResponseDTO> getMess(int myId, int yourId);
}
