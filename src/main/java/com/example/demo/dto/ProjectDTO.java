package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ProjectDTO {
    private Integer id;

    private String name;

    private String completed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
