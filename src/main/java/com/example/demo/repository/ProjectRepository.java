package com.example.demo.repository;

import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

//    @Transactional
//    @Modifying
////    @Query(value = "UPDATE project p SET u.",nativeQuery = true)
//    int updateProject(Project project);
}
