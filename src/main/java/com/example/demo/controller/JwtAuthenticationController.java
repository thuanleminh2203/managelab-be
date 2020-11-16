package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.UserPrincipal;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.service.JwtUserDetailsService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WrapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(ConstUtils.API)
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest rq) {
        log.info("====Start authenticate with username: " + rq.getUsername());
        ResponseEntity<?> responseEntity;
        try {
            authenticate(rq.getUsername(), rq.getPassword());
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(rq.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            UserPrincipal userPrincipal = (UserPrincipal) userDetails;
            responseEntity = WrapperDataResponse.success(new ResponseData(null, ConstUtils.SUCCESS, new JwtResponse(userPrincipal.getId(),token, userPrincipal.getRoleList(), userPrincipal.getUsername(), userPrincipal.getFullName())));
        } catch (Exception e) {
            log.error("====Err authenticate with username: " + rq.getUsername());
            responseEntity = WrapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
        log.info("====End authenticate with username: " + rq.getUsername());
        return responseEntity;
    }

//    @PostMapping("/authenticate/signout")
//    public ResponseEntity<?> logoutUser(Principal principal) {
//        ResponseEntity<?> responseEntity;
//        try {
//            Date date = jwtUserDetailsService.getTimeToken(principal.getName());
//            if (date != null) {
//                jwtUserDetailsService.updateTimeTokenByUsername(principal.getName(), null);
//                responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, null));
//            } else {
//                responseEntity = WapperDataResponse.err(new ResponseData(null, ConstUtils.ERR_BUSINESS, null), HttpStatus.BAD_REQUEST);
//            }
//
//
//        } catch (Exception e) {
//            log.info("===== err ==" + e.getMessage());
//            responseEntity = WapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
//        }
//
//        return responseEntity;
//    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Sai thông tin tài khoản !!");
        }
    }
}
