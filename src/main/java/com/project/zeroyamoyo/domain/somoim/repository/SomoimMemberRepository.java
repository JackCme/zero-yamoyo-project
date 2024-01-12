package com.project.zeroyamoyo.domain.somoim.repository;

import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimMember;
import com.project.zeroyamoyo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SomoimMemberRepository extends JpaRepository<SomoimMember, Long> {
    Optional<SomoimMember> findBySomoimAndUser(Somoim somoim, User user);
}
