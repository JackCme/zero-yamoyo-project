package com.project.zeroyamoyo.domain.interest.entity;

import javax.persistence.*;

@Entity
@Table(name = "interest_category")
public class InterestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;
    private String name;
}
