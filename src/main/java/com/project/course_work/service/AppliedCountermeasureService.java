package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppliedCountermeasureService {
    private final AppliedCountermeasureRepository appliedRepository;
    private final EmergencyRepository emergencyRepository;
    private final CounterMeasureRepository cmRepository;

    public AppliedCountermeasure create(AppliedCountermeasureDTO dto) {
        Emergency emergency = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        CounterMeasure cm = cmRepository.findById(dto.getCountermeasureId())
                .orElseThrow(() -> new RuntimeException("CounterMeasure not found"));

        AppliedCountermeasure entity = new AppliedCountermeasure();
        entity.setEmergency(emergency);
        entity.setCounterMeasure(cm);
        entity.setApplicationDate(dto.getApplicationDate());
        return appliedRepository.save(entity);
    }

    public List<AppliedCountermeasure> getAll() {
        return appliedRepository.findAll();
    }

    public List<AppliedCountermeasure> getByEmergency(Integer emergencyId) {
        return appliedRepository.findByEmergency_Id(emergencyId);
    }
}
