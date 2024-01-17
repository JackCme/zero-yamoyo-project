package com.project.zeroyamoyo.domain.somoim.repository;

import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SomoimRepository extends JpaRepository<Somoim, Long> {
    List<Somoim> findAllByOrderByIdDesc(Pageable page);
    List<Somoim> findByIdLessThanOrderByIdDesc(Long id, Pageable page);
    Boolean existsByIdLessThan(Long id);
}
