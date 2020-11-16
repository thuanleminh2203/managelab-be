package com.example.demo.config;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.utils.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String expired = (String) request.getAttribute("expried");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseData responseData = new ResponseData();
        responseData.setMess("Invalid Login details");
        responseData.setErrCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        if (expired != null) {
            request.removeAttribute("expired");
            responseData.setMess("JWT Token has expired");
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String json = objectMapper.writeValueAsString(responseData);
        response.getWriter().write(json);


    }

}
