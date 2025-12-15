package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.Emergency;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Integer> {

    @Query("SELECT e FROM Emergency e LEFT JOIN FETCH e.facility")
    List<Emergency> findAllWithFacilities();

    List<Emergency> findByFacility_Id(Integer facilityId);

    List<Emergency> findByScale(String scale);

    List<Emergency> findByStartDateAfter(LocalDate date);
}