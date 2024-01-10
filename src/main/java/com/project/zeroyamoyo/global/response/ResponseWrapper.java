package com.project.zeroyamoyo.global.response;

import com.project.zeroyamoyo.global.exception.ResultType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseWrapper<T> {
    @Builder.Default
    private Result result = Result.from(ResultType.OK);
    @Builder.Default
    private T content = null;

}
