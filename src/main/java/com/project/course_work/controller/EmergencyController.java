package com.project.course_work.controller;

import com.project.course_work.dto.EmergencyDTO;
import com.project.course_work.entity.Emergency;
import com.project.course_work.service.EmergencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/emergencies")
@RequiredArgsConstructor
public class EmergencyController {

    private final EmergencyService service;

    @PostMapping
    public Emergency create(@RequestBody EmergencyDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Emergency> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-facility")
    public List<Emergency> getByFacility(@RequestParam Integer id) {
        return service.getByFacility(id);
    }

    @GetMapping("/search")
    public List<Emergency> search(@RequestParam(required = false) String scale,
                                  @RequestParam(required = false) LocalDate afterDate) {
        if (scale != null) return service.getByScale(scale);
        if (afterDate != null) return service.getAfterDate(afterDate);
        return service.getAll();
    }
}
