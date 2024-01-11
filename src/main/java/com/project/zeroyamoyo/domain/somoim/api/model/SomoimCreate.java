package com.project.zeroyamoyo.domain.somoim.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @Builder
    @Getter
    public static class Response {
        private Long somoimId;
        private String name;
        private Integer regionCode;
        private String description;
        private Integer limit;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public static Response fromEntity(Somoim somoim) {
            return Response.builder()
                    .somoimId(somoim.getId())
                    .name(somoim.getName())
                    .regionCode(somoim.getRegionCode())
                    .description(somoim.getDescription())
                    .limit(somoim.getLimit())
                    .createdDate(somoim.getCreatedDate())
                    .modifiedDate(somoim.getModifiedDate())
                    .build();
        }
    }
}
