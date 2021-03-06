package com.example.demo.config;

import com.example.demo.dto.JwtUserInfoDTO;
import com.example.demo.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        final List<String> allowedOrigins = Arrays.asList("http://localhost:3000","*");
        String username = null;
        String jwtToken = null;

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            // Access-Control-Allow-Origin
            String origin = request.getHeader("Origin");
            res.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
            res.setHeader("Vary", "Origin");

            // Access-Control-Max-Age
            res.setHeader("Access-Control-Max-Age", "3600");

            // Access-Control-Allow-Credentials
            res.setHeader("Access-Control-Allow-Credentials", "true");

            // Access-Control-Allow-Methods
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // Access-Control-Allow-Headers
            res.setHeader("Access-Control-Allow-Headers",
                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
        }

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                request.setAttribute("expried", e.getMessage());
                System.out.println("====" + request.getAttribute("expried"));
            } catch (Exception e) {
                System.out.println("=== err token === :" + e.getMessage());
            }
        }

//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserPrincipal userPrincipal = (UserPrincipal) this.jwtUserDetailsService.loadUserByUsername(username);
//            userPrincipal.setToken(jwtToken);
//
//            if (jwtTokenUtil.validateToken(jwtToken, userPrincipal)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userPrincipal, null, userPrincipal.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                // After setting the Authentication in the context, we specify
//                // that the current user is authenticated. So it passes the Spring Security
//                // Configurations successfully.
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        } else {
//            SecurityContextHolder.clearContext();
//        }
        if (username != null) {
            UserPrincipal userPrincipal = (UserPrincipal) this.jwtUserDetailsService.loadUserByUsername(username);
            userPrincipal.setToken(jwtToken);

            if (jwtTokenUtil.validateToken(jwtToken, userPrincipal)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userPrincipal, null, userPrincipal.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails loadUserByJWT(JwtUserInfoDTO jwtUserInfoDTO, List<String> roleList) {
        return UserPrincipal.create(jwtUserInfoDTO, roleList);
    }

}
