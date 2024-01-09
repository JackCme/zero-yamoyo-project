package com.project.zeroyamoyo.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.zeroyamoyo.global.exception.ResultType;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import com.project.zeroyamoyo.global.response.Result;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SecurityExceptionHandler {
    public void writeResponse(HttpServletResponse response, ResultType resultType) throws IOException {
        ResponseWrapper<Object> responseWrapper = ResponseWrapper.builder()
                .result(Result.from(resultType))
                .build();
        String jsonString = new ObjectMapper().writeValueAsString(responseWrapper);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(resultType.getHttpStatus().value());
        response.getWriter().write(jsonString);
        response.flushBuffer();
    }
}
