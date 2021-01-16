package com.example.demo.service;

import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<GrantedAuthority> lst = new ArrayList<>();
        List<String> roles =  getRoleById(user.getId());
        roles.forEach(k -> lst.add(new SimpleGrantedAuthority(k)));

        return new UserPrincipal(user.getId() ,user.getUsername(), user.getPassword(), roles,lst,null, user.getFullName());
    }

    public void save(UserDTO user) throws Exception {
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            throw new Exception("Username is exists !!");
        }
        User data = mapper.convertValue(user, User.class);
        data.setCreatedAt(new Date());
        data.setPassword( new BCryptPasswordEncoder().encode(data.getPassword()));
        User result = userRepository.save(data);
        userRoleRepository.save(new UserRole(result.getId(), 2));
    }

    public List<String> getRoleById(int id) {
        return userRepository.getRoleById(id);
    }

//	public Date getTimeToken(String username) throws UsernameNotFoundException {
//		User user = getUserByUsername(username);
//		System.out.println("====user.getTimeToken()====" + user.getTimeToken());
//		return user.getTimeToken();
//	}

    private User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

//	public void updateTimeTokenByUsername(String username , Date date) throws Exception {
//		Integer id = userRepository.updateTimeTokenByUsername(username, date);
//		if(id < 0) throw new Exception("loi j a");
//	}
}	
