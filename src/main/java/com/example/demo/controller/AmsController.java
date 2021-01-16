package com.example.demo.controller;

import com.example.demo.service.AmsService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping(ConstUtils.API)
@AllArgsConstructor
@CrossOrigin
public class AmsController {
    private final AmsService amsService;

    @PostMapping("/file")
    public ResponseEntity<?> upload(@RequestPart("files") MultipartFile files) {
        {
            log.info("======Start save img" );
            ResponseEntity<?> responseEntity;
            try {
                responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, amsService.uploadFile(files)));
            } catch (Exception e) {
                log.error("======Err save img =========" + e.getMessage());
                responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
            log.info("======End save img =========>" );
            return responseEntity;
        }

    }

}
