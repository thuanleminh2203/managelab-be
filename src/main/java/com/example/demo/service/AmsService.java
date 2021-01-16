package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AmsService {

    String uploadFile(MultipartFile file) throws IOException, Exception;
}
