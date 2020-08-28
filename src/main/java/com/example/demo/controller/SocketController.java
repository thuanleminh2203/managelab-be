package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.MessageModel;

@Controller
@CrossOrigin
public class SocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/user")
	@SendTo("/topic/history")
	public String send(String msg) throws Exception {
		return msg;
	}

	@MessageMapping("/room/{username}")
	public void sendSpecific(@DestinationVariable String username, MessageModel model )
			throws Exception {
		System.out.println("====usernaem==="+username);
		simpMessagingTemplate.convertAndSend("/topic/thuanlm", model);
	}

}
