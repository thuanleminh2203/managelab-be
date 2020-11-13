package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.service.JwtUserDetailsService;
import com.example.utils.ConstUtils;
import com.example.utils.ResponseData;
import com.example.utils.WapperDataResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest rq) {
        ResponseEntity<?> responseEntity;
        try {
//            authenticate(rq.getUsername(), rq.getPassword());
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(rq.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            responseEntity = WapperDataResponse.sucsses(new ResponseData(null, ConstUtils.SUSSCESS, new JwtResponse(token)));
        } catch (Exception e) {
            responseEntity = WapperDataResponse.err(new ResponseData(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }

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
            throw new Exception("INVALID_CREDENTIALS" + e.getMessage(), e);
        }
    }
}
