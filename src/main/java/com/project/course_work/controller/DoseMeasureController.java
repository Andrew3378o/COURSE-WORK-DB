package com.project.course_work.controller;

import com.project.course_work.dto.DoseMeasureDTO;
import com.project.course_work.entity.DoseMeasure;
import com.project.course_work.service.DoseMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/dose-measures")
@RequiredArgsConstructor
public class DoseMeasureController {

    private final DoseMeasureService service;

    @PostMapping
    public DoseMeasure create(@RequestBody DoseMeasureDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<DoseMeasure> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-emergency")
    public List<DoseMeasure> getByEmergency(@RequestParam Integer id) {
        return service.getByEmergency(id);
    }

    @GetMapping("/high")
    public List<DoseMeasure> getHigh(@RequestParam BigDecimal min) {
        return service.getHighDoses(min);
    }
}
