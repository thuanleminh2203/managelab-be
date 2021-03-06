package com.example.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperDataResponse {
	
	public static <T> ResponseEntity<T> success(T body){
		return new ResponseEntity<T>(body, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<T> err(T body, HttpStatus httpStatus){
		return new ResponseEntity<T>(body, httpStatus);
	}
}
