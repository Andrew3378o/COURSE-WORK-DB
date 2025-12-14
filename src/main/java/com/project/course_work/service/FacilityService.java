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

    private final HybridGeocodingService geocodingService;

    public Facility create(FacilityDTO dto) {
        Facility entity = new Facility();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setTechnologyType(dto.getTechnologyType());

        if (dto.getLocation() != null) {
            autoGeocode(entity);
        }

        return repository.save(entity);
    }

    public Facility save(Facility entity) {
        autoGeocode(entity);
        return repository.save(entity);
    }

    public void autoGeocode(Facility facility) {
        if (facility.getLatitude() == null && facility.getLocation() != null) {

            double[] coords = geocodingService.getCoordinates(facility.getLocation());

            if (coords != null) {
                facility.setLatitude(Double.valueOf(coords[0]));
                facility.setLongitude(Double.valueOf(coords[1]));
                System.out.println("DEBUG: Автоматичне геокодування: " + facility.getLocation() +
                        " -> Lat: " + coords[0] + ", Lng: " + coords[1]);
            }
        }
    }

    public Optional<Facility> getById(Integer id) {
        return repository.findById(id);
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