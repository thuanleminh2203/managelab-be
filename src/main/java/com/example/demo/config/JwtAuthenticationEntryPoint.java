package com.example.demo.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		String expired = (String) request.getAttribute("expried");
		System.out.println("=====ppp===" + expired);
		if (expired!=null){
			request.removeAttribute("expired");
			response.addHeader("err", "E401");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT Token has expired");
        }else{
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Login details");
        }
	}


}
