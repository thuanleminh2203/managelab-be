package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Data
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name="full_name")
    private String fullName;

    @Column(name="like_count")
    private int likeCount;

    @Column(name="dislike")
    private int dislike;

    @Column(name="content")
    private String content;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;





}


