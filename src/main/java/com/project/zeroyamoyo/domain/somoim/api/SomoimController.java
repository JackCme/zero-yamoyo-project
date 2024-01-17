package com.project.zeroyamoyo.domain.somoim.api;

import com.project.zeroyamoyo.domain.somoim.api.model.SomoimCreate;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimGet;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimInterestModify;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimModify;
import com.project.zeroyamoyo.domain.somoim.service.SomoimService;
import com.project.zeroyamoyo.global.response.ResponseWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/somoim")
@RequiredArgsConstructor
@Tag(name = "Somoim", description = "소모임 관련 API")
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
    public ResponseWrapper<SomoimModify.Response> modify(@PathVariable("somoimId") Long somoimId, @RequestBody @Valid SomoimModify.Request request) {
        return ResponseWrapper.<SomoimModify.Response>builder()
                .content(somoimService.modifySomoim(somoimId, request))
                .build();
    }

    @PatchMapping("/{somoimId}/interest")
    public ResponseWrapper<SomoimInterestModify.Response> modifySomoimInterest(@PathVariable("somoimId") Long somoimId, @RequestBody @Valid SomoimInterestModify.Request request) {
        return ResponseWrapper.<SomoimInterestModify.Response>builder()
                .content(somoimService.modifySomoimInterest(somoimId, request))
                .build();
    }

    @GetMapping("/list")
    public ResponseWrapper<CursorResult<SomoimGetList.Response>> getList(@ParameterObject @ModelAttribute @Valid SomoimGetList.Request request) {
        return ResponseWrapper.<CursorResult<SomoimGetList.Response>>builder()
                .content(somoimService.getSomoimList(request.getCursorId(), PageRequest.of(0, request.getSize())))
                .build();
    }

    @PostMapping("/{somoimId}/apply")
    public ResponseWrapper<Object> apply(@PathVariable("somoimId") Long somoimId) {
        somoimService.applyToSomoim(somoimId);
        return ResponseWrapper.builder().build();
    }

    @PostMapping("/{somoimId}/accept/{userId}")
    public ResponseWrapper<Object> accept(@PathVariable("somoimId") Long somoimId, @PathVariable("userId") Long userId) {
        somoimService.acceptMember(somoimId, userId);
        return ResponseWrapper.builder().build();
    }

    @PostMapping("/{somoimId}/decline/{userId}")
    public ResponseWrapper<Object> decline(@PathVariable("somoimId") Long somoimId, @PathVariable("userId") Long userId) {
        somoimService.declineMember(somoimId, userId);
        return ResponseWrapper.builder().build();
    }


}
