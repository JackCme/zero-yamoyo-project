package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.domain.user.entity.User;
import com.project.zeroyamoyo.global.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class Somoim extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer regionCode;
    private String description;
    private Integer limit;
    @Column(name = "interest_id")
    private Long interestId;
    @ManyToOne
    @JoinColumn(name = "interest_id", insertable = false, updatable = false)
    private Interest interest;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

}
