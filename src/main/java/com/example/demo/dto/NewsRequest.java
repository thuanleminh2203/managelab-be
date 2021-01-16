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
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;



}
