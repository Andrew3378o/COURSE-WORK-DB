package com.project.course_work.controller;

import com.project.course_work.dto.AppliedCountermeasureDTO;
import com.project.course_work.entity.AppliedCountermeasure;
import com.project.course_work.service.AppliedCountermeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applied-countermeasures")
@RequiredArgsConstructor
public class AppliedCountermeasureController {

    private final AppliedCountermeasureService service;

    @PostMapping
    public AppliedCountermeasure create(@RequestBody AppliedCountermeasureDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AppliedCountermeasure> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-emergency")
    public List<AppliedCountermeasure> getByEmergency(@RequestParam Integer id) {
        return service.getByEmergency(id);
    }

}
