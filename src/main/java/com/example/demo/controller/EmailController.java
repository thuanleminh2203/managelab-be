package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("sendMail1")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		System.out.println("message" + "You successfully uploaded " + file.getOriginalFilename() + "!");

		return "message" + "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
}
