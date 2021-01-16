package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.MessageModel;
import com.example.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
//@AllArgsConstructor
public class SocketController {

    @Autowired
    private SimpMessageSendingOperations template ;
    @Autowired
    private  MessageService messageService;

    @MessageMapping("/user")
    @SendTo("/topic/history")
    public String send(String msg) {
        return msg;
    }

    @MessageMapping("/room/{username}")
    public void sendSpecific(@DestinationVariable String username, MessageModel model)
            throws Exception {
        System.out.println("====usernaem===" + username);
        template.convertAndSend("/topic/thuanlm", model);
    }


    @MessageMapping("/message")
//    @SendToUser("/queue/reply")
    public void sendSpecific(@Payload MessageDTO msg, Principal principal) {
        try {
            var data = messageService.save(msg);
//            simpMessagingTemplate.convertAndSend("/topic/all/"+msg.getTo(), data);
//            simpMessagingTemplate.convertAndSend("/topic/all/", data);
            template.convertAndSend("/topic/chat/" + msg.getTo(), data);
//            System.out.println("=====principal======"+principal);
//            template.convertAndSendToUser("thuanlm@gmail.com","/queue/reply", data);
//            return data.toString();
//            simpMessagingTemplate.convertAndSend("/secured/user/queue/specific-user" + msg.getTo(), messageService.save(msg));
        } catch (Exception e) {
            log.error("===Err when send mess == " + e.getMessage());
        }
//    return "thuandepzaivlll";
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

//    @MessageMapping("/message")
//    public void sendSpecific(@Payload MessageDTO msg) {
//        try {
//            var data = messageService.save(msg);
////            simpMessagingTemplate.convertAndSend("/topic/all/"+msg.getTo(), data);
////            simpMessagingTemplate.convertAndSend("/topic/all/", data);
//            simpMessagingTemplate.convertAndSend("/topic/chat/" + msg.getTo(), data);
//
////            simpMessagingTemplate.convertAndSend("/secured/user/queue/specific-user" + msg.getTo(), messageService.save(msg));
//        } catch (Exception e) {
//            log.error("===Err when send mess == " + e.getMessage());
//        }
//
//    }

}
