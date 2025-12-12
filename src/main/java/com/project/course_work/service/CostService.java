package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CostService {
    private final CostRepository costRepository;
    private final FacilityRepository facilityRepository;
    private final EmergencyRepository emergencyRepository;
    private final AppliedCountermeasureRepository appliedRepository;
    private final ImpactRepository impactRepository;

    public Cost create(CostDTO dto) {
        Facility facility = facilityRepository.findById(dto.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        Emergency emergency = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        AppliedCountermeasure applied = null;
        if (dto.getAppliedId() != null) {
            applied = appliedRepository.findById(dto.getAppliedId())
                    .orElseThrow(() -> new RuntimeException("AppliedCountermeasure not found"));
        }

        Impact impact = null;
        if (dto.getImpactId() != null) {
            impact = impactRepository.findById(dto.getImpactId())
                    .orElseThrow(() -> new RuntimeException("Impact not found"));
        }

        Cost entity = new Cost();
        entity.setFacility(facility);
        entity.setEmergency(emergency);
        entity.setAppliedCountermeasure(applied);
        entity.setImpact(impact);
        entity.setCostValue(dto.getCostValue());
        entity.setCostDate(dto.getCostDate());

        return costRepository.save(entity);
    }

    public List<Cost> getAll() {
        return costRepository.findAll();
    }

    public List<Cost> getByEmergency(Integer emergencyId) {
        return costRepository.findByEmergency_Id(emergencyId);
    }

    public List<Cost> getByFacility(Integer facilityId) {
        return costRepository.findByFacility_Id(facilityId);
    }
}
