package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.AppliedCountermeasure;
import java.util.List;

@Repository
public interface AppliedCountermeasureRepository extends JpaRepository<AppliedCountermeasure, Integer> {

    List<AppliedCountermeasure> findByEmergency_Id(Integer emergencyId);

}
