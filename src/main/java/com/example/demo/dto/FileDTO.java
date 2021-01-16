package com.example.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@JsonSerialize
@Getter
@Setter
public class FileDTO {
    private MultipartFile[] avatar;
}
