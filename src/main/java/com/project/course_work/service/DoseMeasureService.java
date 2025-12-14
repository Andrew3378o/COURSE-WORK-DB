package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional; // ДОДАНО: Для методу getById

@Service
@RequiredArgsConstructor
public class DoseMeasureService {
    private final DoseMeasureRepository doseRepository;
    private final EmergencyRepository emergencyRepository;

    public DoseMeasure create(DoseMeasureDTO dto) {
        Emergency emergency = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        DoseMeasure entity = new DoseMeasure();
        entity.setEmergency(emergency);
        entity.setDoseValue(dto.getDoseValue());
        entity.setMeasurementDate(dto.getMeasurementDate());
        return doseRepository.save(entity);
    }

    public Optional<DoseMeasure> getById(Integer id) {
        return doseRepository.findById(id);
    }

    public DoseMeasure save(DoseMeasure entity) {
        // Якщо entity має ID, це оновлення; якщо ні — створення.
        return doseRepository.save(entity);
    }

    public List<DoseMeasure> getAll() {
        return doseRepository.findAll();
    }

    public List<DoseMeasure> getByEmergency(Integer emergencyId) {
        return doseRepository.findByEmergency_Id(emergencyId);
    }

    public List<DoseMeasure> getHighDoses(BigDecimal value) {
        return doseRepository.findByDoseValueGreaterThan(value);
    }
}