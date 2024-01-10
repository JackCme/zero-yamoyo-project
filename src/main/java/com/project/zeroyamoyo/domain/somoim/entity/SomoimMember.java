package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "somoim_member")
public class SomoimMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "somoim_id")
    private Somoim somoim;
    private MemberRole role;
    private LocalDateTime appliedAt;
    private LocalDateTime enrolledAt;
}
