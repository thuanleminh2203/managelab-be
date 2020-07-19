package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE user u set u.time_token = :time_token WHERE u.username = :username", nativeQuery= true )
	int updateTimeTokenByUsername(@Param("username") String username, @Param("time_token") Date timeToken);
	
	@Query(value= "SELECT r.role FROM role r JOIN user_role ur on r.id = ur.role_id WHERE ur.user_id = :id", nativeQuery = true)
	List<String> getRoleById(@Param("id") int id);
	
	@Query(value = "SELECT u.username FROM user u", nativeQuery = true)
	Page<UserDetailDTO> getUsername(Pageable pageable); 
}