package com.project.zeroyamoyo.domain.interest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "interest_id")
    private List<InterestCategory> interestCategoryList;
}
