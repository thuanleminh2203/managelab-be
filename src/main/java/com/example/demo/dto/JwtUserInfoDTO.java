package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserInfoDTO {

    private int id;
    private String username;
    private String password;
}
