package com.example.demo.serviceImpl;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ObjectMapper mapper;


    @Override
    public ProjectDTO create(ProjectDTO projectDto, String createBy) {
        Project project = mapper.convertValue(projectDto, Project.class);
        project.setCreatedBy(createBy);
        project.setCreatedAt(new Date());
        return mapper.convertValue(projectRepository.save(project), ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> getAll() {
        return projectRepository.findAll().parallelStream().map(k -> mapper.convertValue(k, ProjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getListByConditions(String collum, int value) {
        return projectRepository.getListByConditions(collum, value);
    }

    @Override
    public void delete(int id) throws Exception {
        projectRepository.deleteById(id);
    }

//    @Override
//    public Project update(Project project) {
////        return projectRepository.save(project);
//    }
}

