package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/hello")
public class HelloController {
	
	@ResponseBody
	@PostMapping
	public String hello(Authentication authentication) {
		System.out.println("=====authen===" + authentication.getName());
		System.out.println("=====authen===" + authentication.getPrincipal());
		return "hello";
	}
}
