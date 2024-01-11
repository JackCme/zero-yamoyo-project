package com.project.zeroyamoyo.domain.somoim.repository;

import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomoimInterestRepository extends JpaRepository<SomoimInterest, Long> {
}
