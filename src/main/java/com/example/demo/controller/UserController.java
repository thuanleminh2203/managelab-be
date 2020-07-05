package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.JwtUserDetailsService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.ResponseErr;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody UserDTO user){
		ResponseEntity responseEntity;
		try {
			jwtUserDetailsService.save(user);
			responseEntity = new ResponseEntity(new ResponseData(null, ConstUtils.SUSSCESS, user), HttpStatus.OK);
		
		} catch (Exception e) {
			responseEntity =  new ResponseEntity( new ResponseData(null,e.getMessage(),null), HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
