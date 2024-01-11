package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.interest.entity.Interest;

import javax.persistence.*;

@Entity
@Table(name = "somoim_interest")
public class SomoimInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String interestCategory;
    @Column(name = "interest_id")
    private Long interestId;
    @ManyToOne
    @JoinColumn(name = "interest_id", insertable = false, updatable = false)
    private Interest interest;
    @OneToOne
    @JoinColumn(name = "somoim_id")
    private Somoim somoim;
}
