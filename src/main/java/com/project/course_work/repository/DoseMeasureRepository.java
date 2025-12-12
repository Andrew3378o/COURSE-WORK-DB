package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.DoseMeasure;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DoseMeasureRepository extends JpaRepository<DoseMeasure, Integer> {

    List<DoseMeasure> findByEmergency_Id(Integer emergencyId);

    List<DoseMeasure> findByDoseValueGreaterThan(BigDecimal value);
}