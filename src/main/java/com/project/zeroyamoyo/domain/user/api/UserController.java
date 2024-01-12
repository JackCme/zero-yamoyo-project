package com.project.zeroyamoyo.domain.user.api;

import com.project.zeroyamoyo.domain.user.api.model.TokenVO;
import com.project.zeroyamoyo.domain.user.api.model.UserJoin;
import com.project.zeroyamoyo.domain.user.api.model.UserLogin;
import com.project.zeroyamoyo.domain.user.service.UserService;
import com.project.zeroyamoyo.global.jwt.JwtFilter;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "사용자 인증 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
})
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "사용자 회원가입 API")
    @SecurityRequirements
    public ResponseWrapper<Object> join(@RequestBody @Valid UserJoin.Request request) {
        userService.join(request);
        return ResponseWrapper.builder().build();
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인 API")
    @SecurityRequirements
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
