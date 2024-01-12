package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.user.entity.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserJoin {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    @Schema(title = "회원가입 요청정보")
    public static class Request {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String nickname;
        @NotNull
        private Gender gender;
        private Integer regionCode;
        private String description;
        @NotEmpty
        private List<Interest> interests;

        @Getter
        public static class Interest {
            private Integer id;
            private List<String> category;
        }

    }
}
