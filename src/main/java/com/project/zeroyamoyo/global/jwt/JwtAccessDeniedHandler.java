package com.project.zeroyamoyo.global.jwt;

import com.project.zeroyamoyo.global.config.SecurityExceptionHandler;
import com.project.zeroyamoyo.global.exception.ResultType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler extends SecurityExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        this.writeResponse(response, ResultType.UNAUTHORIZED);
    }
}
