package com.project.zeroyamoyo.domain.user.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.user.entity.Gender;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
        @Schema(description = "로그인 이메일", example = "john.doe@example.com")
        private String email;
        @NotBlank
        @Schema(description = "로그인 비밀번호(알파벳 대/소문자 최소 1개씩, 숫자 1개, 특수기호 1개, 최소 8자리", example = "aStr0ngP@ssword1!")
        private String password;
        @NotBlank
        @Schema(description = "사용자 닉네임", example = "John Doe")
        private String nickname;
        @NotNull
        @Schema(description = "사용자 성별", example = "MALE")
        private Gender gender;
        @Schema(description = "사용자 지역코드", example = "1")
        private Integer regionCode;
        @Schema(description = "사용자 자기소개", example = "This is my description")
        private String description;
        @NotEmpty
        @Schema(description = "사용자 관심사")
        private List<Interest> interests;

        @Schema(title = "사용자 관심사 요청정보")
        @Getter
        public static class Interest {
            @Schema(description = "관심주제 id")
            private Integer id;
            @ArraySchema( arraySchema =  @Schema(description = "세부 관심사 목록",example ="[\"배구\", \"볼링\"]"))
            private List<String> category;
        }

    }
}
