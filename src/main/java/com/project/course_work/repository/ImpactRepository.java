package com.project.course_work.repository;

import com.project.course_work.entity.Impact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // ОБОВ'ЯЗКОВО!
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImpactRepository extends JpaRepository<Impact, Integer> {

    @Query("SELECT i FROM Impact i LEFT JOIN FETCH i.emergency LEFT JOIN FETCH i.doseMeasure")
    List<Impact> findAllWithEmergencyAndDose();

    List<Impact> findByPopulationGroup(String populationGroup);

    List<Impact> findByEmergency_Id(Integer emergencyId);

    List<Impact> findByPopulationGroupIgnoreCase(String group);
}