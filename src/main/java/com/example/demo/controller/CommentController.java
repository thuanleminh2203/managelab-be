package com.example.demo.controller;

import com.example.demo.config.AuthInfo;
import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.CommentRequest;
import com.example.demo.service.CommentService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(ConstUtils.API)
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> save(@AuthInfo UserPrincipal userPrincipal, @RequestBody CommentRequest rq
    ) {
        log.info("======Start save Comment" + userPrincipal.getUsername());
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, commentService.save(rq,userPrincipal)));
        } catch (Exception e) {
            log.error("======Err save Comment =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End save Comment =========>" + userPrincipal.getUsername());
        return responseEntity;
    }
}
