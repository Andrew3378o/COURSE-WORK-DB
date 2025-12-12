package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cost")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CostID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FacilityID")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "EmergencyID")
    private Emergency emergency;

    @ManyToOne
    @JoinColumn(name = "AppliedID")
    private AppliedCountermeasure appliedCountermeasure;

    @ManyToOne
    @JoinColumn(name = "ImpactID")
    private Impact impact;

    @Column(name = "CostValue", precision = 12, scale = 2)
    private BigDecimal costValue;

    @Column(name = "CostDate")
    private LocalDate costDate;
}