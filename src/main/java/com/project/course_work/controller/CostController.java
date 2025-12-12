package com.project.course_work.controller;

import com.project.course_work.dto.CostDTO;
import com.project.course_work.entity.Cost;
import com.project.course_work.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/costs")
@RequiredArgsConstructor
public class CostController {

    private final CostService service;

    @PostMapping
    public Cost create(@RequestBody CostDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Cost> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-emergency")
    public List<Cost> getByEmergency(@RequestParam Integer id) {
        return service.getByEmergency(id);
    }

    @GetMapping("/by-facility")
    public List<Cost> getByFacility(@RequestParam Integer id) {
        return service.getByFacility(id);
    }
}