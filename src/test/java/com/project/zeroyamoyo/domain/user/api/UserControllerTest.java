package com.project.zeroyamoyo.domain.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.zeroyamoyo.domain.user.service.UserService;
import com.project.zeroyamoyo.global.config.SecurityConfig;
import com.project.zeroyamoyo.global.jwt.JwtAccessDeniedHandler;
import com.project.zeroyamoyo.global.jwt.JwtAuthenticationEntryPoint;
import com.project.zeroyamoyo.global.jwt.JwtFilter;
import com.project.zeroyamoyo.global.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = UserController.class, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                JwtTokenProvider.class, JwtAuthenticationEntryPoint.class, JwtAccessDeniedHandler.class, JwtFilter.class
        })
})
@Import(SecurityConfig.class)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void join() {
        // Given
        // When
        // Then
        throw new RuntimeException();
    }

    @Test
    void login() {
        // Given
        // When
        // Then
        throw new RuntimeException();
    }
}