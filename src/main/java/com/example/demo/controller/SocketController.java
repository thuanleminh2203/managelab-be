package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MessageModel;
import com.example.demo.dto.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin
public class SocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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


    @MessageMapping("/secured/room")
    public void sendSpecific(@Payload MessageDTO msg, Principal user, @Header("simpSessionId") String sessionId) {
        System.out.println("/secured/user/queue/specific-user-user" +sessionId);
        OutputMessage out = new OutputMessage(
                msg.getFrom(),
                msg.getText(),
                new SimpleDateFormat("HH:mm").format(new Date()));
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(),"/secured/user/queue/specific-user-user" +sessionId, out);
//        simpMessagingTemplate.convertAndSend("/secured/user/queue/specific-user-user" +sessionId, out);
    }
}
