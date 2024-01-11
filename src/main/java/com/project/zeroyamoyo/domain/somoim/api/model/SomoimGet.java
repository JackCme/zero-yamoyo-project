package com.project.zeroyamoyo.domain.somoim.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.interest.api.model.vo.InterestVo;
import com.project.zeroyamoyo.domain.somoim.api.model.vo.SomoimMemberVo;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class SomoimGet {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    public static class Response extends SomoimBase.Response {
        private final InterestVo somoimInterest;
        private final List<SomoimMemberVo> members;

        public Response(Somoim somoim) {
            super(somoim);
            this.somoimInterest = InterestVo.from(somoim.getSomoimInterest());
            this.members = somoim.getSomoimMembers().stream().map(SomoimMemberVo::from).collect(Collectors.toList());
        }
    }
}
