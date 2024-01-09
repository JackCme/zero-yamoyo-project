package com.project.zeroyamoyo.global.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.global.exception.ResultType;
import lombok.AllArgsConstructor;

@JsonNaming(SnakeCaseStrategy.class)
@AllArgsConstructor
public class Result {
    private String code;
    private String message;

    public static Result from(ResultType resultType) {
        return new Result(
                Integer.toString(resultType.getStatusCode()),
                resultType.getDescription()
        );
    }
}
