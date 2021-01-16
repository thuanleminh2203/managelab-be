package com.example.demo.controller;

import com.example.demo.config.AuthInfo;
import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.service.ProjectService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;

@CrossOrigin
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(ConstUtils.API + "/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ObjectMapper mapper;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDTO rq, @AuthInfo UserPrincipal userPrincipal) {
        log.info("======Start create Project =========" + rq.toString());

        ResponseEntity<?> responseEntity;
        try {
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, projectService.create(rq, userPrincipal.getUsername())));
        } catch (Exception e) {
            log.error("======Err create Project =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End create Project =========");
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable int id) {
        log.info("======Start delete Project by Id =========" + id);

        ResponseEntity<?> responseEntity;
        try {
            projectService.delete(id);
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, null));
        } catch (Exception e) {
            log.error("======Err delete Project by Id =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End delete Project by Id =========");
        return responseEntity;
    }

//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody ProjectDTO rq, Principal principal) {
//        log.info("======Start update Project =========");
//        ResponseEntity<?> responseEntity;
//        try {
//            if (rq.getProjectId() == null || rq.getProjectId() == 0) {
//                return WapperDataResponse.err(new ResponseData(null, "Id is not null", null), HttpStatus.BAD_REQUEST);
//            }
//            Optional<Project> projectOptional = projectService.findById(rq.getProjectId());
//            if (!projectOptional.isPresent()) {
//                return WapperDataResponse.err(new ResponseData(null, "Not found data with id = " + rq.getProjectId(), null), HttpStatus.BAD_REQUEST);
//            }
//            Project project = projectOptional.get();
////            project.setCompleted(rq.getCompleted());
////            project.setStartTime(rq.getStartTime());
////            project.setEndTime(rq.getEndTime());
//            project.setUpdatedBy(principal.getName());
//            project.setUpdatedAt(new Date());
//            project.setProjectName(rq.getProjectName());
//            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, projectService.update(project)));
//
//
//        } catch (Exception e) {
//            log.error("======Err update Project =========" + e.getMessage());
//            responseEntity = WapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
//        }
//        log.info("======End update Project =========");
//        return responseEntity;
//    }

    @GetMapping
    public ResponseEntity<?> getAll(@AuthInfo UserPrincipal userPrincipal) {
        log.info("======Start getAll Project =========");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserPrincipal userPrincipal =  (UserPrincipal) authentication.getPrincipal();
        System.out.println("======" + userPrincipal.getToken());
        ResponseEntity<?> responseEntity;
        try {
//            System.out.println(projectService.getListByConditions("project_id",1));
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, projectService.getAll()));
        } catch (Exception e) {
            log.error("======Err getAll Project =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End getAll Project =========");
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable("id") Integer id) {
        log.info("======Start getById Project =========");
        ResponseEntity<?> responseEntity;
        try {
            ProjectDTO projectDTO = mapper.convertValue(projectService.findById(id).get(), ProjectDTO.class);
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, projectDTO));
        } catch (Exception e) {
            log.error("======Err getById Project =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End getById Project =========");
        return responseEntity;
    }
}
