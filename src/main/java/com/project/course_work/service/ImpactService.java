package com.project.course_work.service;

import com.project.course_work.dto.ImpactDTO;
import com.project.course_work.entity.DoseMeasure;
import com.project.course_work.entity.Emergency;
import com.project.course_work.entity.Impact;
import com.project.course_work.repository.DoseMeasureRepository;
import com.project.course_work.repository.EmergencyRepository;
import com.project.course_work.repository.ImpactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImpactService {
    private final ImpactRepository repository;
    private final EmergencyRepository emergencyRepository;
    private final DoseMeasureRepository doseMeasureRepository;

    public Impact create(ImpactDTO dto) {
        Emergency emergency = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        DoseMeasure dose = null;
        if (dto.getDoseId() != null) {
            dose = doseMeasureRepository.findById(dto.getDoseId())
                    .orElseThrow(() -> new RuntimeException("Dose Measure not found"));
        }

        Impact entity = new Impact();
        entity.setEmergency(emergency);
        entity.setDoseMeasure(dose);
        entity.setPopulationGroup(dto.getPopulationGroup());
        entity.setExposureType(dto.getExposureType());
        entity.setHealthEffect(dto.getHealthEffect());
        return repository.save(entity);
    }

    public Impact save(Impact entity) {
        return repository.save(entity);
    }

    public Optional<Impact> getById(Integer id) {
        return repository.findById(id);
    }

    public List<Impact> getAll() {
        return repository.findAll();
    }

    public List<Impact> getByEmergency(Integer emergencyId) {
        return repository.findByEmergency_Id(emergencyId);
    }

    public List<Impact> getByGroup(String group) {
        return repository.findByPopulationGroupIgnoreCase(group);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}