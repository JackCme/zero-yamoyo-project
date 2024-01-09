package com.project.zeroyamoyo.global.jwt;

import com.project.zeroyamoyo.global.config.SecurityExceptionHandler;
import com.project.zeroyamoyo.global.exception.ResultType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint extends SecurityExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        this.writeResponse(response, ResultType.ACCESS_DENIED);
    }
}
