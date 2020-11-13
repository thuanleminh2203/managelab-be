package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thuanlm
 * @created at 11/13/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDTO implements Serializable {
    private int id;
    private String fullName;
}
