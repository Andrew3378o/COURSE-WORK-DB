package com.project.course_work.service;


import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // ДОДАНО: Для методу getById

@Service
@RequiredArgsConstructor
public class ImpactService {
    private final ImpactRepository impactRepository;
    private final EmergencyRepository emergencyRepository;
    private final DoseMeasureRepository doseRepository;

    public Impact create(ImpactDTO dto) {
        Emergency emergency = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        DoseMeasure dose = null;
        if (dto.getDoseId() != null) {
            dose = doseRepository.findById(dto.getDoseId())
                    .orElseThrow(() -> new RuntimeException("DoseMeasure not found"));
        }

        Impact entity = new Impact();
        entity.setEmergency(emergency);
        entity.setDoseMeasure(dose);
        entity.setPopulationGroup(dto.getPopulationGroup());
        entity.setExposureType(dto.getExposureType());
        entity.setHealthEffect(dto.getHealthEffect());
        return impactRepository.save(entity);
    }

    public Optional<Impact> getById(Integer id) {
        return impactRepository.findById(id);
    }

    public Impact save(Impact entity) {
        // Якщо entity має ID, це оновлення; якщо ні — створення.
        return impactRepository.save(entity);
    }

    public List<Impact> getAll() {
        return impactRepository.findAll();
    }

    public List<Impact> getByEmergency(Integer emergencyId) {
        return impactRepository.findByEmergency_Id(emergencyId);
    }

    public List<Impact> getByGroup(String group) {
        return impactRepository.findByPopulationGroup(group);
    }
}