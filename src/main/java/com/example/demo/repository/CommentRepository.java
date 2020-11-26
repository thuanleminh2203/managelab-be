package com.example.demo.repository;

import com.example.demo.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

    @Query(value = "SELECT * from comment c where c.news_id = ?1 ORDER BY c.comment_id asc ",nativeQuery = true)
    List<CommentEntity> getAllComments(int idNews);
}
