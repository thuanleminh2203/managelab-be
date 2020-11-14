package com.example.demo.controller;

import com.example.demo.config.AuthInfo;
import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserSearchDTO;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.UserService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WapperDataResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final JwtUserDetailsService jwtUserDetailsService;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        ResponseEntity<?> responseEntity;
        try {
            jwtUserDetailsService.save(user);
            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, null));

        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

//    @GetMapping("get-username")
//    public ResponseEntity<?> getUserName(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//                                         @RequestParam(name = "size", required = false, defaultValue = "5") int size,
//                                         @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//                                         @RequestParam(name = "sortby", required = false, defaultValue = "id") String... sortby) {
//        ResponseEntity<?> responseEntity;
//        try {
//            Sort sortTable = sort.equals("ASC") ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
//            Page<UserDetailDTO> lst = userService.getUsername(PageRequest.of(page, size, sortTable));
//            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, lst.getContent()));
//
//
//        } catch (Exception e) {
//            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }

    @GetMapping("search-by-name/{username}")
    public ResponseEntity<?> getByName(@PathVariable String username) {
        ResponseEntity<?> responseEntity;
        try {
            List<UserSearchDTO> lst = userService.getByName(username);
            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, lst));


        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, userService.findAll()));
        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
