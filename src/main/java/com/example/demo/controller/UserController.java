package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDetailDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.UserService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WapperDataResponse;


@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		ResponseEntity<?> responseEntity;
		try {
//			jwtUserDetailsService.save(user);
			responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, user));

		} catch (Exception e) {
			responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@GetMapping("get-username")
	public ResponseEntity<?> getUserName(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(name = "sortby", required = false, defaultValue = "id") String... sortby) {
		ResponseEntity<?> responseEntity;
		try {
			Sort sortTable = sort.equals("ASC") ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
			Page<UserDetailDTO> lst = userRepo.getUsername(PageRequest.of(page, size, sortTable));
			responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, lst.getContent()));


		} catch (Exception e) {
			responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/find-all")
	public ResponseEntity<?> findAll(){
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, userService.findAll()));
		} catch (Exception e) {
			responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
