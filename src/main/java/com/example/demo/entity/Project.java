package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author thuanlm
 * @created at 11/12/2020
 */
@Entity
@Table(name = "project")
@Data
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "completed")
    private Integer completed;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date endTime;
    @Column(name = "owner")
    private String owner;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date startTime;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;


}
