package com.project.zeroyamoyo.domain.somoim.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SomoimCreate {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    public static class Request {
        @NotBlank
        private String name;
        @NotNull
        private Integer regionCode;
        @NotBlank
        private String description;
        @NotNull
        @Min(1)
        @Max(300)
        private Integer limit;
        @NotNull
        private Integer interestId;
    }
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response extends SomoimBase.Response {
        public Response(Somoim somoim) {
            super(somoim);
        }
    }
}
