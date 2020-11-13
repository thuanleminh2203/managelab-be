package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "user_details")
@Data
public class UserDetails {

	@Id
	@Column
	private int id;

	@Column
	private String fullname;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "khoa")
	private int khoa;
	
	@Column(name = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "id_user")
	@JsonIgnore
	private int idUser;


	
	
}
