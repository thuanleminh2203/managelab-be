package com.example.demo.config;

import com.example.demo.dto.JwtUserInfoDTO;
import com.example.demo.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    @Setter
    @Getter
    private int id;
    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    @Getter
    private List<String> roleList;
    private Collection<? extends GrantedAuthority> authorities;
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String fullName;

    public static UserPrincipal create(JwtUserInfoDTO user, List<String> roleList) {
        List<GrantedAuthority> authorities = roleList
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(toList());
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                roleList,
                authorities,
                null,
                user.getFullName()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
