package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLogin {
    @Getter
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
