package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dosemeasures")
public class DoseMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoseID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EmergencyID")
    private Emergency emergency;

    @Column(name = "DoseValue", precision = 10, scale = 2)
    private BigDecimal doseValue;

    @Column(name = "MeasurementDate")
    private LocalDateTime measurementDate;
}