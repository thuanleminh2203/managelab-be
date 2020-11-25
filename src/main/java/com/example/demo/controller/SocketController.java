package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MessageModel;
import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/user")
    @SendTo("/topic/history")
    public String send(String msg) {
        return msg;
    }

    @MessageMapping("/room/{username}")
    public void sendSpecific(@DestinationVariable String username, MessageModel model)
            throws Exception {
        System.out.println("====usernaem===" + username);
        simpMessagingTemplate.convertAndSend("/topic/thuanlm", model);
    }


    @MessageMapping("/message")
    public void sendSpecific(@Payload MessageDTO msg) {
        try {
            var data = messageService.save(msg);
//            simpMessagingTemplate.convertAndSend("/topic/all/"+msg.getTo(), data);
//            simpMessagingTemplate.convertAndSend("/topic/all/", data);
            simpMessagingTemplate.convertAndSend("/topic/chat/" + msg.getTo(), data);

//            simpMessagingTemplate.convertAndSend("/secured/user/queue/specific-user" + msg.getTo(), messageService.save(msg));
        } catch (Exception e) {
            log.error("===Err when send mess == " + e.getMessage());
        }

    }

}
