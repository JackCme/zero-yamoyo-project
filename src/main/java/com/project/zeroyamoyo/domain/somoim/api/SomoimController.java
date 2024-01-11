package com.project.zeroyamoyo.domain.somoim.api;

import com.project.zeroyamoyo.domain.somoim.api.model.SomoimCreate;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimGet;
import com.project.zeroyamoyo.domain.somoim.service.SomoimService;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/somoim")
@RequiredArgsConstructor
public class SomoimController {
    private final SomoimService somoimService;
    @PostMapping("/")
    public ResponseWrapper<SomoimCreate.Response> create(@RequestBody @Valid SomoimCreate.Request request) {
        return ResponseWrapper.<SomoimCreate.Response>builder()
                .content(somoimService.create(request))
                .build();
    }
    @GetMapping("/{somoimId}")
    public ResponseWrapper<SomoimGet.Response> get(@PathVariable("somoimId") Long somoimId) {
        return ResponseWrapper.<SomoimGet.Response>builder()
                .content(somoimService.getSomoim(somoimId))
                .build();
    }
    @PatchMapping("/{somoimId}")
    public ResponseWrapper<Object> modify(@PathVariable("somoimId") Long somoimId) {
        return null;
    }

    @GetMapping("/list")
    public ResponseWrapper<Object> getList() {
        return null;
    }

    @PostMapping("/apply/{somoimId}")
    public ResponseWrapper<Object> apply(@PathVariable("somoimId") Long somoimId) {
        return null;
    }


}
