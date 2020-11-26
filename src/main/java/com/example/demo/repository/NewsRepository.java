package com.example.demo.repository;

import com.example.demo.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Query(value = "SELECT * from news n ORDER BY n.news_id DESC limit ?1,?2", nativeQuery = true)
    List<NewsEntity> getNews(int pageIndex, int pageSize);
}
