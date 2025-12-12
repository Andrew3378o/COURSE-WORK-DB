package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.Emergency;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Integer> {

    List<Emergency> findByFacility_Id(Integer facilityId);

    List<Emergency> findByScale(String scale);

    List<Emergency> findByStartDateAfter(LocalDate date);
}