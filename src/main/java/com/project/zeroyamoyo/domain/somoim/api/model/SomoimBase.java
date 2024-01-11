package com.project.zeroyamoyo.domain.somoim.api.model;

import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import lombok.Getter;

import java.time.LocalDateTime;


public class SomoimBase {
    @Getter
    public static class Response {
        private final Long somoimId;
        private final String name;
        private final Integer regionCode;
        private final String description;
        private final Integer limit;
        private final LocalDateTime createdDate;
        private final LocalDateTime modifiedDate;

        public Response(Somoim somoim) {
            this.somoimId = somoim.getId();
            this.name = somoim.getName();
            this.regionCode = somoim.getRegionCode();
            this.description = somoim.getDescription();
            this.limit = somoim.getLimit();
            this.createdDate = somoim.getCreatedDate();
            this.modifiedDate = somoim.getModifiedDate();
        }
    }

}
