package com.example.demo.controller;

import com.example.demo.config.AuthInfo;
import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.ListNewsSearchResponse;
import com.example.demo.dto.NewsRequest;
import com.example.demo.service.NewsService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(ConstUtils.API)
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<?> getAll(@AuthInfo UserPrincipal userPrincipal,
                                    @RequestParam(name = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        log.info("======Start getAll News =========>" + userPrincipal.getUsername());
        ResponseEntity<?> responseEntity;
        try {
            var data = newsService.getNews(pageIndex, pageSize);

            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, new ListNewsSearchResponse(data.size(), data)));
        } catch (Exception e) {
            log.error("======Err getAll News =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End getAll News =========" + userPrincipal.getUsername());
        return responseEntity;
    }

    @PostMapping("/news")
    public ResponseEntity<?> save(@AuthInfo UserPrincipal userPrincipal, @RequestBody NewsRequest newsRequest
    ) {
        log.info("======Start save News =========>" + userPrincipal.getUsername());
        ResponseEntity<?> responseEntity;
        try {
            newsRequest.setUserId(userPrincipal.getId());
            newsRequest.setUsername(userPrincipal.getUsername());
            newsRequest.setFullName(userPrincipal.getFullName());
            var data = newsService.save(newsRequest);
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, data));
        } catch (Exception e) {
            log.error("======Err save News =========" + e.getMessage());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("======End save News =========>" + userPrincipal.getUsername());
        return responseEntity;
    }
}
