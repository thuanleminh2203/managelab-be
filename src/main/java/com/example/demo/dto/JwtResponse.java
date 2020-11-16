package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {
	private int id;
	private String token;
	private List<String> roles;
	private String username;
	private String fullName;
}
