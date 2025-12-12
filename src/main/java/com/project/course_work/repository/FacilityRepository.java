package com.project.course_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.course_work.entity.Facility;
import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {

    Facility findByName(String name);

    List<Facility> findByNameContainingIgnoreCase(String namePart);

    List<Facility> findByLocationContainingIgnoreCase(String location);
}
