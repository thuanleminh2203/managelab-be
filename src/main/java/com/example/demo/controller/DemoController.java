package com.example.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class DemoController {
	private String demo;

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	
}
