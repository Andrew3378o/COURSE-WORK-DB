package com.project.course_work.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AppliedCountermeasureDTO {
    private Integer emergencyId;
    private Integer countermeasureId;
    private LocalDate applicationDate;
}
