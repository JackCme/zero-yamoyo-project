package com.project.zeroyamoyo.domain.user.entity;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "user_interest")
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;
    private String interestCategory;
    private Long interestId;
    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
