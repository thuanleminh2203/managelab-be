package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSearchDTO implements Serializable {
    private int newsId;
    private int userId;
    private String fullName;
    private int like;
    private int dislike;
    private String content;
    private String avatar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    List<CommentDTO> comments;
}
