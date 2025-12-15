package com.project.course_work.controller;

import com.project.course_work.entity.*;
import com.project.course_work.repository.*;
import com.project.course_work.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    private final FacilityRepository facilityRepository;
    private final EmergencyRepository emergencyRepository;
    private final CounterMeasureRepository counterMeasureRepository;
    private final DoseMeasureRepository doseMeasureRepository;
    private final ImpactRepository impactRepository;
    private final CostRepository costRepository;

    private final FacilityService facilityService;
    private final EmergencyService emergencyService;
    private final CounterMeasureService counterMeasureService;
    private final DoseMeasureService doseMeasureService;
    private final ImpactService impactService;
    private final CostService costService;

    public WebController(FacilityRepository facilityRepository, EmergencyRepository emergencyRepository,
                         CounterMeasureRepository counterMeasureRepository, DoseMeasureRepository doseMeasureRepository,
                         ImpactRepository impactRepository, CostRepository costRepository,
                         FacilityService facilityService, EmergencyService emergencyService,
                         CounterMeasureService counterMeasureService, DoseMeasureService doseMeasureService,
                         ImpactService impactService, CostService costService) {
        this.facilityRepository = facilityRepository;
        this.emergencyRepository = emergencyRepository;
        this.counterMeasureRepository = counterMeasureRepository;
        this.doseMeasureRepository = doseMeasureRepository;
        this.impactRepository = impactRepository;
        this.costRepository = costRepository;
        this.facilityService = facilityService;
        this.emergencyService = emergencyService;
        this.counterMeasureService = counterMeasureService;
        this.doseMeasureService = doseMeasureService;
        this.impactService = impactService;
        this.costService = costService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("facilitiesCount", facilityRepository.count());
        model.addAttribute("emergenciesCount", emergencyRepository.count());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }

    @GetMapping("/ui/facilities")
    public String showFacilities(
            @RequestParam(value = "query", required = false) String query,
            Model model) {

        List<Facility> facilities;

        if (query != null && !query.trim().isEmpty()) {
            facilities = facilityService.searchByName(query);
            if (facilities.isEmpty()) {
                facilities = facilityService.searchByLocation(query);
            }
            model.addAttribute("searchQuery", query);
        } else {
            facilities = facilityRepository.findAll();
        }

        model.addAttribute("list", facilities);
        model.addAttribute("activePage", "facilities");
        return "facilities";
    }

    @GetMapping("/ui/facilities/new")
    public String showNewFacilityForm(Model model) {
        model.addAttribute("facility", new Facility());
        model.addAttribute("isNew", true);
        model.addAttribute("activePage", "facilities");
        return "facility_form";
    }
    @GetMapping("/ui/facilities/edit/{id}")
    public String showEditFacilityForm(@PathVariable Integer id, Model model) {
        Facility facility = facilityService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found"));
        model.addAttribute("facility", facility);
        model.addAttribute("isNew", false);
        model.addAttribute("activePage", "facilities");
        return "facility_form";
    }
    @PostMapping("/ui/facilities/save")
    public String saveFacility(@ModelAttribute("facility") Facility facility) {
        facilityService.save(facility);
        return "redirect:/ui/facilities";
    }

    @GetMapping("/ui/facilities/delete/{id}")
    public String deleteFacility(@PathVariable Integer id) {
        try {
            facilityService.deleteById(id);
        } catch (Exception e) {
            System.err.println("Помилка при видаленні об'єкта: " + e.getMessage());
        }
        return "redirect:/ui/facilities";
    }

    @GetMapping("/ui/emergencies")
    public String showEmergencies(
            @RequestParam(value = "scale", required = false) String scale,
            @RequestParam(value = "type", required = false) String type,
            Model model) {

        List<Emergency> emergencies = emergencyRepository.findAll();

        if ((scale != null && !scale.isEmpty()) || (type != null && !type.isEmpty())) {
            emergencies = emergencies.stream()
                    .filter(e -> (scale == null || scale.isEmpty() || (e.getScale() != null && e.getScale().equalsIgnoreCase(scale))))
                    .filter(e -> (type == null || type.isEmpty() || (e.getCommunalType() != null && e.getCommunalType().equalsIgnoreCase(type))))
                    .collect(Collectors.toList());
        }

        model.addAttribute("possibleScales", List.of("INES 1", "INES 2", "INES 3", "INES 4", "INES 5", "INES 6", "INES 7"));
        model.addAttribute("possibleTypes", List.of("Chemical", "Radiation", "Natural", "Biological"));

        model.addAttribute("list", emergencies);
        model.addAttribute("activePage", "emergencies");
        model.addAttribute("selectedScale", scale);
        model.addAttribute("selectedType", type);

        return "emergencies";
    }

    @GetMapping("/ui/emergencies/new")
    public String showNewEmergencyForm(Model model) {
        model.addAttribute("emergency", new Emergency());
        model.addAttribute("facilities", facilityRepository.findAll());
        model.addAttribute("isNew", true);
        model.addAttribute("activePage", "emergencies");
        return "emergency_form";
    }
    @GetMapping("/ui/emergencies/edit/{id}")
    public String showEditEmergencyForm(@PathVariable Integer id, Model model) {
        Emergency emergency = emergencyService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found"));
        model.addAttribute("emergency", emergency);
        model.addAttribute("facilities", facilityRepository.findAll());
        model.addAttribute("isNew", false);
        return "emergency_form";
    }
    @PostMapping("/ui/emergencies/save")
    public String saveEmergency(@ModelAttribute("emergency") Emergency emergency) {
        emergencyService.save(emergency);
        return "redirect:/ui/emergencies";
    }


    @GetMapping("/ui/countermeasures")
    public String showCountermeasures(Model model) {
        model.addAttribute("list", counterMeasureRepository.findAll());
        model.addAttribute("activePage", "countermeasures");
        return "countermeasures";
    }
    @GetMapping("/ui/countermeasures/new")
    public String showNewCountermeasureForm(Model model) {
        model.addAttribute("counterMeasure", new CounterMeasure());
        model.addAttribute("isNew", true);
        return "countermeasure_form";
    }
    @GetMapping("/ui/countermeasures/edit/{id}")
    public String showEditCountermeasureForm(@PathVariable Integer id, Model model) {
        CounterMeasure cm = counterMeasureService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CounterMeasure not found"));
        model.addAttribute("counterMeasure", cm);
        model.addAttribute("isNew", false);
        return "countermeasure_form";
    }
    @PostMapping("/ui/countermeasures/save")
    public String saveCountermeasure(@ModelAttribute("counterMeasure") CounterMeasure cm) {
        counterMeasureService.save(cm);
        return "redirect:/ui/countermeasures";
    }


    @GetMapping("/ui/dose-measures")
    public String showDoseMeasures(Model model) {
        model.addAttribute("list", doseMeasureRepository.findAll());
        model.addAttribute("activePage", "dose-measures");
        return "dose-measures";
    }
    @GetMapping("/ui/dose-measures/new")
    public String showNewDoseMeasureForm(Model model) {
        model.addAttribute("doseMeasure", new DoseMeasure());
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("isNew", true);
        return "dose_measure_form";
    }
    @GetMapping("/ui/dose-measures/edit/{id}")
    public String showEditDoseMeasureForm(@PathVariable Integer id, Model model) {
        DoseMeasure dm = doseMeasureService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DoseMeasure not found"));
        model.addAttribute("doseMeasure", dm);
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("isNew", false);
        model.addAttribute("activePage", "dose-measures");
        return "dose_measure_form";
    }
    @PostMapping("/ui/dose-measures/save")
    public String saveDoseMeasure(@ModelAttribute("doseMeasure") DoseMeasure dm) {
        doseMeasureService.save(dm);
        return "redirect:/ui/dose-measures";
    }

    @GetMapping("/ui/dose-measures/delete/{id}")
    public String deleteDoseMeasure(@PathVariable Integer id) {
        try {
            doseMeasureService.deleteById(id);
        } catch (Exception e) {
            System.err.println("Помилка при видаленні виміру дози: " + e.getMessage());
        }
        return "redirect:/ui/dose-measures";
    }


    @GetMapping("/ui/impacts")
    public String showImpacts(Model model) {
        model.addAttribute("list", impactRepository.findAll());
        model.addAttribute("activePage", "impacts");
        return "impacts";
    }
    @GetMapping("/ui/impacts/new")
    public String showNewImpactForm(Model model) {
        model.addAttribute("impact", new Impact());
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("doseMeasures", doseMeasureRepository.findAll());
        model.addAttribute("isNew", true);
        model.addAttribute("activePage", "impacts");
        return "impact_form";
    }
    @GetMapping("/ui/impacts/edit/{id}")
    public String showEditImpactForm(@PathVariable Integer id, Model model) {
        Impact impact = impactService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Impact not found"));
        model.addAttribute("impact", impact);
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("doseMeasures", doseMeasureRepository.findAll());
        model.addAttribute("isNew", false);
        model.addAttribute("activePage", "impacts");
        return "impact_form";
    }
    @PostMapping("/ui/impacts/save")
    public String saveImpact(@ModelAttribute("impact") Impact impact) {
        impactService.save(impact);
        return "redirect:/ui/impacts";
    }

    @GetMapping("/ui/impacts/delete/{id}")
    public String deleteImpact(@PathVariable Integer id) {
        try {
            impactService.deleteById(id);
        } catch (Exception e) {
            System.err.println("Помилка при видаленні впливу: " + e.getMessage());
        }
        return "redirect:/ui/impacts";
    }

    @GetMapping("/ui/costs")
    public String showCosts(Model model) {
        model.addAttribute("list", costRepository.findAll());
        return "costs";
    }
    @GetMapping("/ui/costs/new")
    public String showNewCostForm(Model model) {
        model.addAttribute("cost", new Cost());
        model.addAttribute("facilities", facilityRepository.findAll());
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("impacts", impactRepository.findAllWithEmergencyAndDose()); // ВИПРАВЛЕНО
        model.addAttribute("isNew", true); // ДОДАНО
        model.addAttribute("activePage", "costs"); // ДОДАНО
        return "cost_form";
    }
    @GetMapping("/ui/costs/edit/{id}")
    public String showEditCostForm(@PathVariable Integer id, Model model) {
        Cost cost = costService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cost not found"));
        model.addAttribute("cost", cost);
        model.addAttribute("facilities", facilityRepository.findAll());
        model.addAttribute("emergencies", emergencyRepository.findAllWithFacilities()); // ВИПРАВЛЕНО
        model.addAttribute("impacts", impactRepository.findAllWithEmergencyAndDose()); // ВИПРАВЛЕНО
        model.addAttribute("isNew", false); // ДОДАНО
        model.addAttribute("activePage", "costs"); // ДОДАНО
        return "cost_form";
    }
    @PostMapping("/ui/costs/save")
    public String saveCost(@ModelAttribute("cost") Cost cost) {
        costService.save(cost);
        return "redirect:/ui/costs";
    }

    @GetMapping("/ui/map")
    public String showMap(Model model) {

        List<Facility> facilities = facilityRepository.findAll();
        List<Emergency> emergencies = emergencyRepository.findAll();

        facilities.forEach(facility -> {
            if (facility.getLatitude() == null && facility.getLocation() != null) {
                facilityService.autoGeocode(facility);
                facilityService.save(facility);
            }
        });

        facilities = facilityRepository.findAll();
        emergencies = emergencyRepository.findAll();

        model.addAttribute("facilities", facilities);
        model.addAttribute("emergencies", emergencies);
        model.addAttribute("activePage", "map");

        return "map";
    }
}