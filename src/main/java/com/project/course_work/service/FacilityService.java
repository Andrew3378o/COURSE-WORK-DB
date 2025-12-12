package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository repository;

    public Facility create(FacilityDTO dto) {
        Facility entity = new Facility();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setTechnologyType(dto.getTechnologyType());
        return repository.save(entity);
    }

    public List<Facility> getAll() {
        return repository.findAll();
    }

    public List<Facility> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Facility> searchByLocation(String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }
}