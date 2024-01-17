package com.project.zeroyamoyo.domain.somoim.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.api.model.vo.SomoimInterestVo;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import lombok.Getter;
import org.springframework.lang.Nullable;

public class SomoimGetList {
    @Getter
    public static class Request {
        private static final int DEFAULT_SIZE = 10;
        @Nullable
        private Long cursorId;
        private Integer size = DEFAULT_SIZE;
    }
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    public static class Response extends SomoimBase.Response {
        private final SomoimInterestVo somoimInterest;
        private final Integer memberCount;
        public Response(Somoim somoim) {
            super(somoim);
            this.somoimInterest = SomoimInterestVo.from(somoim.getSomoimInterest());
            this.memberCount = somoim.getSomoimMembers().size();
        }
    }
}
