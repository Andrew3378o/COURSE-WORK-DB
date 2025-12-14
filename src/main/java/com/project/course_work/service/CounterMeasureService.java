package com.project.course_work.service;

import com.project.course_work.dto.*;
import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // ДОДАНО

@Service
@RequiredArgsConstructor
public class CounterMeasureService {
    private final CounterMeasureRepository repository;

    public CounterMeasure create(CounterMeasureDTO dto) {
        CounterMeasure entity = new CounterMeasure();
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        return repository.save(entity);
    }

    public Optional<CounterMeasure> getById(Integer id) {
        return repository.findById(id);
    }

    public CounterMeasure save(CounterMeasure entity) {
        return repository.save(entity);
    }

    public List<CounterMeasure> getAll() {
        return repository.findAll();
    }

    public List<CounterMeasure> getByType(String type) {
        return repository.findByType(type);
    }
}