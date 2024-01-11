package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.user.entity.User;
import com.project.zeroyamoyo.global.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Somoim extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer regionCode;
    private String description;
    private Integer limit;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @OneToOne(mappedBy = "somoim")
    private SomoimInterest somoimInterest;
    @OneToMany(mappedBy = "somoim")
    private List<SomoimMember> somoimMembers;

    @Builder
    public Somoim(Long id, String name, Integer regionCode, String description, Integer limit, User creator) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.description = description;
        this.limit = limit;
        this.creator = creator;
    }
}
