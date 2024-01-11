package com.project.zeroyamoyo.domain.somoim.entity;

import com.project.zeroyamoyo.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "somoim_member")
@NoArgsConstructor
@Getter
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
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private LocalDateTime appliedAt;
    private LocalDateTime enrolledAt;

    @Builder
    public SomoimMember(User user, Somoim somoim, MemberRole role, LocalDateTime appliedAt, LocalDateTime enrolledAt) {
        this.user = user;
        this.somoim = somoim;
        this.role = role;
        this.appliedAt = appliedAt;
        this.enrolledAt = enrolledAt;
    }
}
