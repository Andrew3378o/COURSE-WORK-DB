package com.project.course_work.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CostDTO {
    private Integer facilityId;
    private Integer emergencyId;
    private Integer appliedId;
    private Integer impactId;
    private BigDecimal costValue;
    private LocalDate costDate;
}
