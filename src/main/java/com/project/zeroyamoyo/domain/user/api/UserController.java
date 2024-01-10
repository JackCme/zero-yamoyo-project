package com.project.zeroyamoyo.domain.user.api;

import com.project.zeroyamoyo.domain.user.api.model.TokenVO;
import com.project.zeroyamoyo.domain.user.api.model.UserJoin;
import com.project.zeroyamoyo.domain.user.api.model.UserLogin;
import com.project.zeroyamoyo.domain.user.service.UserService;
import com.project.zeroyamoyo.global.jwt.JwtFilter;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseWrapper<Object> join(@RequestBody @Valid UserJoin.Request request) {
        userService.join(request);
        return ResponseWrapper.builder().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<TokenVO>> login(@RequestBody @Valid UserLogin.Request request) {
        TokenVO tokenVO = userService.login(request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenVO.getToken());

        return new ResponseEntity<>(
                ResponseWrapper.<TokenVO>builder()
                        .content(tokenVO)
                        .build(),
                httpHeaders,
                HttpStatus.OK
        );
    }
}
