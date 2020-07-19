package com.example.demo.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.dto.EmailDTO;

public interface EmailService {
	public void sendMail(EmailDTO mail , HttpServletRequest request) throws MessagingException , IOException;
}
