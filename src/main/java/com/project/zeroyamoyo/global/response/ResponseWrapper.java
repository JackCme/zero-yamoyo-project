package com.project.zeroyamoyo.global.response;

import lombok.Builder;

@Builder
public class ResponseWrapper<T> {
    private Result result;
    @Builder.Default
    private T content = null;

}
