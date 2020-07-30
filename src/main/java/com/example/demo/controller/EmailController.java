package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmailDTO;
import com.example.demo.service.EmailService;
import com.example.utils.ResponseData;
import com.example.utils.WapperDataResponse;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("sendMail")
	public ResponseEntity<?> sendMail(@ModelAttribute EmailDTO emailDTO , HttpServletRequest request) {
		ResponseEntity<?> responseEntity;
		try {
			emailService.sendMail(emailDTO , request);
			responseEntity = WapperDataResponse.sucsses(new ResponseData());
		} catch (Exception e) {
			responseEntity = WapperDataResponse.err(new ResponseData(null, e.getMessage(), null),
					HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

}
