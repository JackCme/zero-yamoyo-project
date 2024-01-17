package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLogin {
    @Getter
    @AllArgsConstructor
    @Schema(title = "로그인 요청정보")
    public static class Request {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
