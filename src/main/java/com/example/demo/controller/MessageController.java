package com.example.demo.controller;

import com.example.demo.config.AuthInfo;
import com.example.demo.config.UserPrincipal;
import com.example.demo.service.MessageService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(ConstUtils.API + "/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable int id, @AuthInfo UserPrincipal userPrincipal) {
        log.info("======Start getAll message =========" + userPrincipal.getUsername() + "=== with user_id " + id);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, messageService.getMess(userPrincipal.getId(),id)));
        } catch (Exception e) {
            log.error("======Err getAll message =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End getAll message =========");
        return responseEntity;
    }
}


