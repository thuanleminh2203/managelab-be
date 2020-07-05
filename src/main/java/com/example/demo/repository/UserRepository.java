package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.time_token = :time_token WHERE u.username = :username", nativeQuery= true )
	int updateTimeTokenByUsername(@Param("username") String username, @Param("time_token") Date timeToken);
	
	@Query(value= "SELECT r.role FROM Role r INNER JOIN UserRole ur on r.id = ur.id WHERE ur.user_id = :id", nativeQuery = true)
	List<String> getRoleById(@Param("id") int id);
}
