package com.project.course_work.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DoseMeasureDTO {
    private Integer emergencyId;
    private BigDecimal doseValue;
    private LocalDate measurementDate;
}
