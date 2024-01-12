package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.global.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "somoim_interest")
@Getter
@NoArgsConstructor
public class SomoimInterest extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String interestCategory;
    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;
    @OneToOne
    @JoinColumn(name = "somoim_id")
    private Somoim somoim;

    @Builder
    public SomoimInterest(String interestCategory, Interest interest, Somoim somoim) {
        this.interestCategory = interestCategory;
        this.interest = interest;
        this.somoim = somoim;
    }

    public SomoimInterest updatedSomoimInterest(Interest interest, String category) {
        this.interest = interest;
        this.interestCategory = category;
        return this;
    }
}
