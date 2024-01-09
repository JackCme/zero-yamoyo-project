package com.project.zeroyamoyo.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GlobalException extends RuntimeException {
    private final ResultType resultType;
}
