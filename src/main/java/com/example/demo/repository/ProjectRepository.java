package com.example.demo.repository;

import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

//    @Transactional
//    @Modifying
////    @Query(value = "UPDATE project p SET u.",nativeQuery = true)
//    int updateProject(Project project);

    @Query(value = "SELECT p.:column = :value from project p",nativeQuery = true)
    List<Project> getListByConditions(@Param("column") String column, @Param("value") int value);
}
