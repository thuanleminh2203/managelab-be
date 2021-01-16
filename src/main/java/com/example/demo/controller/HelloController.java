//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.example.demo.dto.UserDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import net.minidev.json.JSONObject;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/hello")
//public class HelloController {
//
//	@Autowired
//	private DemoController demo;
//
////	@Autowired
////	private WebClientComponent webClientComponent;
//
//	WebClient client3 = WebClient.create("http://localhost:8080/register");
//
//	@ResponseBody
//	@PostMapping
//	public UserDTO hello(Authentication authentication) throws JsonMappingException, JsonProcessingException {
//
//		JSONObject jsonObject = new JSONObject();
//
//		UserDTO turnover = new UserDTO();
//		turnover.setUsername("lollllsa");
//		turnover.setPassword("okkkkk");
//		ParameterizedTypeReference<UserDTO> typeRef = new ParameterizedTypeReference<UserDTO>() {
//		};
////		ResponseData details =  client3.method(HttpMethod.POST).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////				.accept(MediaType.APPLICATION_JSON).body(Mono.just(turnover), UserDTO.c).retrieve()
////				.bodyToMono(ResponseData.class).block();
//
////		UserDTO details = webClientComponent.callInternalService(typeRef, turnover, HttpMethod.POST, "http://localhost:8080/register");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		UserDTO details =
//				webClientComponent.callInternalService(typeRef, turnover,
//				HttpMethod.POST, "http://localhost:8080/register", UserDTO.class);
////		UserDTO use = (UserDTO) details.getData();
//		System.out.println("== d======data=====" + details.getPassword() + details.getUsername());
////		use
//		return details;
//	}
//
//}
