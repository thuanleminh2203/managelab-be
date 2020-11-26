package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest implements Serializable {
    private int userId;
    private String username;
    private String fullName;
    private String content;
    private String avatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;



}
