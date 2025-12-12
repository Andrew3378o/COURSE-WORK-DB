package com.project.course_work.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmergencyDTO {
    private Integer facilityId;
    private LocalDate startDate;
    private String scale;
    private String communalType;
    private String description;
}
