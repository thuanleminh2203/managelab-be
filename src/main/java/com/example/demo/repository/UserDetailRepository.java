package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.entity.UserDetails;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Integer>{
	
	@Query(value ="SELECT ud.id, ud.birthday , ud.fullname, ud.khoa, ud.phone_number, u.username from user_details ud JOIN user u on ud.id_user = u.id", nativeQuery=true)
	public List<UserDetailDTO> getAll();
}
