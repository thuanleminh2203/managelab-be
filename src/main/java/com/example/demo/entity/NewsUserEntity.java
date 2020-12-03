package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author thuanlm
 * @created at 12/1/2020
 */
@Entity
@Table(name="news_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUserEntity {
    @Id
    @Column(name="news_user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int newsUserId;

    @Column(name = "news_id")
    private int newsId;

    @Column(name = "user_id")
    private int userId;

}
