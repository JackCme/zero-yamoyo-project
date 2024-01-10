package com.project.zeroyamoyo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResultType {
    OK(HttpStatus.OK, 2000, "OK")
    , INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, 4000, "올바른 요청이 아닙니다")
    , UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4010, "인증된 요청이 아닙니다")
    , ACCESS_DENIED(HttpStatus.FORBIDDEN, 4030, "접근 권한이 없습니다")
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "서버 에러")
    , EMAIL_NOT_VALID(HttpStatus.BAD_REQUEST,4000, "올바른 이메일 형식이 아닙니다")
    , PASSWORD_NOT_VALID(HttpStatus.BAD_REQUEST,4000, "비밀번호의 형식이 올바르지 않습니다")
    , USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,4000, "이미 사용자가 존재합니다")
    ;
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String description;
}
