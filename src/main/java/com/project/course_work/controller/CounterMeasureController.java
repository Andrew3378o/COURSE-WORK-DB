package com.project.course_work.controller;

import com.project.course_work.dto.CounterMeasureDTO;
import com.project.course_work.entity.CounterMeasure;
import com.project.course_work.service.CounterMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/countermeasures")
@RequiredArgsConstructor
public class CounterMeasureController {

    private final CounterMeasureService service;

    @PostMapping
    public CounterMeasure create(@RequestBody CounterMeasureDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CounterMeasure> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-type")
    public List<CounterMeasure> getByType(@RequestParam String type) {
        return service.getByType(type);
    }
}