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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO implements Serializable {
    private int commentId;
    private int userId;
    private String username;
    private String fullName;
    private String content;
    private String avatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;
}
