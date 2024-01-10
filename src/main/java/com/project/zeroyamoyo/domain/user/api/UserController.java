package com.project.zeroyamoyo.domain.user.api;

import com.project.zeroyamoyo.domain.user.api.model.UserJoin;
import com.project.zeroyamoyo.domain.user.api.model.UserLogin;
import com.project.zeroyamoyo.domain.user.service.UserService;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
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
    public ResponseWrapper<UserLogin.Response> login(@RequestBody @Valid UserLogin.Request request) {
        return null;
    }
}
