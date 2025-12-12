package com.project.course_work.controller;


import com.project.course_work.dto.FacilityDTO;
import com.project.course_work.entity.Facility;
import com.project.course_work.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService service;

    @PostMapping
    public Facility create(@RequestBody FacilityDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Facility> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<Facility> search(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String location) {
        if (name != null) return service.searchByName(name);
        if (location != null) return service.searchByLocation(location);
        return service.getAll();
    }
}
