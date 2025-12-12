package com.project.course_work.controller;

import com.project.course_work.dto.ImpactDTO;
import com.project.course_work.entity.Impact;
import com.project.course_work.service.ImpactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/impacts")
@RequiredArgsConstructor
public class ImpactController {

    private final ImpactService service;

    @PostMapping
    public Impact create(@RequestBody ImpactDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Impact> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-emergency")
    public List<Impact> getByEmergency(@RequestParam Integer id) {
        return service.getByEmergency(id);
    }

    @GetMapping("/by-group")
    public List<Impact> getByGroup(@RequestParam String group){
        return service.getByGroup(group);
    }
}
