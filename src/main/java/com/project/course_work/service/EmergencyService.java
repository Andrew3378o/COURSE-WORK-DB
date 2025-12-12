package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyService {
    private final EmergencyRepository emergencyRepository;
    private final FacilityRepository facilityRepository;

    public Emergency create(EmergencyDTO dto) {
        Facility facility = facilityRepository.findById(dto.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        Emergency entity = new Emergency();
        entity.setFacility(facility);
        entity.setStartDate(dto.getStartDate());
        entity.setScale(dto.getScale());
        entity.setCommunalType(dto.getCommunalType());
        entity.setDescription(dto.getDescription());
        return emergencyRepository.save(entity);
    }

    public List<Emergency> getAll() {
        return emergencyRepository.findAll();
    }

    public List<Emergency> getByFacility(Integer facilityId) {
        return emergencyRepository.findByFacility_Id(facilityId);
    }

    public List<Emergency> getByScale(String scale) {
        return emergencyRepository.findByScale(scale);
    }

    public List<Emergency> getAfterDate(LocalDate date) {
        return emergencyRepository.findByStartDateAfter(date);
    }
}