package com.project.zeroyamoyo.global.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final ResultType resultType;

    public GlobalException(ResultType resultType) {
        super();
        this.resultType = resultType;
    }

    public GlobalException(String message) {
        super(message);
        resultType = null;
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
        resultType = null;
    }

    public GlobalException(Throwable cause) {
        super(cause);
        resultType = null;
    }

    protected GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        resultType = null;
    }
}
