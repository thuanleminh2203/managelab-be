package com.example.demo.repository;

import com.example.demo.entity.NewsUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thuanlm
 * @created at 12/1/2020
 */
@Repository
public interface NewsUserRepository extends JpaRepository<NewsUserEntity,Integer> {
}
