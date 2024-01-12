package com.project.zeroyamoyo.domain.somoim.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.api.model.vo.SomoimInterestVo;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SomoimInterestModify {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    public static class Request {
        @NotNull
        private Integer interestId;
        private List<String> category;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    public static class Response {
        private final SomoimInterestVo somoimInterest;
        public Response(SomoimInterest somoimInterest) {
            this.somoimInterest = SomoimInterestVo.from(somoimInterest);
        }
    }

}
