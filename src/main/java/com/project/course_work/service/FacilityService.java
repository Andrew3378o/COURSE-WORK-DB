package com.project.course_work.service;

import com.project.course_work.dto.FacilityDTO;
import com.project.course_work.entity.Facility;
import com.project.course_work.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Facility> getById(Integer id) {
        return repository.findById(id);
    }

    public Facility save(Facility entity) {
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