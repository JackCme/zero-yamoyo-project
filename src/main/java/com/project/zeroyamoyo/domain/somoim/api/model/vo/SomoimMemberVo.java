package com.project.zeroyamoyo.domain.somoim.api.model.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.zeroyamoyo.domain.somoim.entity.MemberRole;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimMember;
import com.project.zeroyamoyo.domain.user.entity.Gender;
import com.project.zeroyamoyo.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SomoimMemberVo {
    private Long id;
    private MemberRole role;
    private LocalDateTime enrolledAt;
    private String nickname;
    private Gender gender;
    private String description;

    public static SomoimMemberVo from(SomoimMember somoimMember) {
        User memberUserInfo = somoimMember.getUser();
        return SomoimMemberVo.builder()
                .id(memberUserInfo.getId())
                .role(somoimMember.getRole())
                .enrolledAt(somoimMember.getEnrolledAt())
                .nickname(memberUserInfo.getNickname())
                .gender(memberUserInfo.getGender())
                .description(memberUserInfo.getDescription())
                .build();
    }
}
