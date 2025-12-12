package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.CounterMeasure;
import java.util.List;

@Repository
public interface CounterMeasureRepository extends JpaRepository<CounterMeasure, Integer> {
    List<CounterMeasure> findByType(String type);
}