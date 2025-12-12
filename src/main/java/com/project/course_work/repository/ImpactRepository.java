package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.Impact;
import java.util.List;

@Repository
public interface ImpactRepository extends JpaRepository<Impact, Integer> {

    List<Impact> findByPopulationGroup(String populationGroup);

    List<Impact> findByEmergency_Id(Integer emergencyId);
}
