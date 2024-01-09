package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLogin {
    public static class Request {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    public static class Response {
        private String token;
    }
}
