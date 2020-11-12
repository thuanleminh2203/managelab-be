package com.example.demo.service;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
     ProjectDTO create(ProjectDTO project, String createOrUpdateBy) throws Exception;

     List<ProjectDTO> getAll() throws  Exception;

     Optional<Project> findById(Integer id) throws Exception;

//     Project update(Project project) throws Exception;

//    public int update(Project project) throws Exception;

}
