package com.project.zeroyamoyo.domain.interest.api.model.vo;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.domain.interest.entity.InterestCategory;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class InterestVo {
    private Long id;
    private String name;
    private List<String> category;

    public static InterestVo from(SomoimInterest somoimInterest) {
        return InterestVo.from(somoimInterest.getInterest(), somoimInterest.getInterestCategory());
    }

    public static InterestVo from(Interest interest) {
        return InterestVo.builder()
                .id(interest.getId())
                .name(interest.getName())
                .category(interest.getInterestCategoryList()
                        .stream()
                        .map(InterestCategory::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public static InterestVo from(Interest interest, String categoryString) {
        return InterestVo.builder()
                .id(interest.getId())
                .name(interest.getName())
                .category(categoryString != null
                        ? Arrays.stream(categoryString.split(",")).collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }
}
