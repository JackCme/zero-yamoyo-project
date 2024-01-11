package com.project.zeroyamoyo.domain.interest.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "interest_category")
@Getter
public class InterestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String name;
}
