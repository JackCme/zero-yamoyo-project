package com.project.zeroyamoyo.domain.somoim.repository;

import com.project.zeroyamoyo.domain.somoim.entity.SomoimMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomoimMemberRepository extends JpaRepository<SomoimMember, Long> {
}
