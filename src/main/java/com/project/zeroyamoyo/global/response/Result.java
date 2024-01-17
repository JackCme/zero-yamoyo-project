package com.project.zeroyamoyo.global.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.global.exception.ResultType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(SnakeCaseStrategy.class)
@AllArgsConstructor
@Getter
@Schema(title = "공통 응답")
public class Result {
    @Schema(description = "응답 코드")
    private String code;
    @Schema(description = "응답 메세지")
    private String message;

    public static Result from(ResultType resultType) {
        return new Result(
                Integer.toString(resultType.getStatusCode()),
                resultType.getDescription()
        );
    }
}
