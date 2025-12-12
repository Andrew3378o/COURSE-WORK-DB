package com.project.course_work.dto;

import lombok.Data;

@Data
public class ImpactDTO {
    private Integer emergencyId;
    private Integer doseId;
    private String populationGroup;
    private String exposureType;
    private String healthEffect;
}
