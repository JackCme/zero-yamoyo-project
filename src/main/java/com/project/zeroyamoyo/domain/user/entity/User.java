package com.project.zeroyamoyo.domain.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Integer region_code;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String description;
    private LocalDateTime registeredAt;
}
