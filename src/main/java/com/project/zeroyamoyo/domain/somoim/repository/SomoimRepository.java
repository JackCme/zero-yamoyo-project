package com.project.zeroyamoyo.domain.somoim.repository;

import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomoimRepository extends JpaRepository<Somoim, Long> {
}
