package com.project.zeroyamoyo.domain.somoim.api;

import com.project.zeroyamoyo.domain.somoim.api.model.SomoimCreate;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/somoim")
public class SomoimController {

    @PostMapping("/")
    public ResponseWrapper<Object> create(@RequestBody @Valid SomoimCreate.Request request) {
        return null;
    }

    @GetMapping("/{somoimId}")
    public ResponseWrapper<Object> get(@PathVariable("somoimId") Long somoimId) {
        return null;
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
