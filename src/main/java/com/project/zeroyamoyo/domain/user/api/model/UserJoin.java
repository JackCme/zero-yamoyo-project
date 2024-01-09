package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.user.entity.Gender;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserJoin {
    public static class Request {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String nickname;
        @NotBlank
        private Gender gender;
        private Integer regionCode;
        private String description;
    }

    public static class Response {
        private Long userId;
        private String email;
    }
}
