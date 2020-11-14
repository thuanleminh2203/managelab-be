package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @author thuanlm
 * @created at 11/14/2020
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@AuthenticationPrincipal(expression = "@fetchUser.apply(#this)", errorOnInvalidType=true)
public @interface AuthInfo {
}
