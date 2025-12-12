package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.Cost;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CostRepository extends JpaRepository<Cost, Integer> {

    List<Cost> findByEmergency_Id(Integer emergencyId);

    @Query("SELECT SUM(c.costValue) FROM Cost c WHERE c.emergency.id = :emergencyId")
    BigDecimal calculateTotalCostForEmergency(Integer emergencyId);

    List<Cost> findByFacility_Id(Integer facilityId);
}
