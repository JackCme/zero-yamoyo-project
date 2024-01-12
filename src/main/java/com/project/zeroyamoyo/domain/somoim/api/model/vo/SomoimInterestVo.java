package com.project.zeroyamoyo.domain.somoim.api.model.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SomoimInterestVo {
    private Long interestId;
    private String interestName;
    private List<String> category;
    private LocalDateTime modifiedDate;

    public static SomoimInterestVo from(SomoimInterest somoimInterest) {
        List<String> categoryList = somoimInterest.getInterestCategory() != null
                ? Arrays.stream(somoimInterest.getInterestCategory().split(",")).collect(Collectors.toList())
                : new ArrayList<>();
        return SomoimInterestVo.builder()
                .interestId(somoimInterest.getInterest().getId())
                .interestName(somoimInterest.getInterest().getName())
                .category(categoryList)
                .modifiedDate(somoimInterest.getModifiedDate())
                .build();
    }
}
