package com.example.demo.serviceImpl;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.entity.MessageEntity;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ObjectMapper mapper;

    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        MessageEntity messageEntity = mapper.convertValue(messageDTO, MessageEntity.class);
        MessageEntity messageEntity1  = messageRepository.save(messageEntity);
        System.out.println("====messageEntity1===" + messageEntity1.getMessageId());
        System.out.println();
        return mapper.convertValue(messageRepository.save(messageEntity),  new TypeReference<>() {
        });
    }

    @Override
    public List<MessageResponseDTO> getMess(int myId, int yourId) {
        return mapper.convertValue(messageRepository.getMess(myId, yourId),  new TypeReference<>() {
        });
    }
}
