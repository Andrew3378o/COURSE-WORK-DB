package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "impact")
public class Impact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImpactID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EmergencyID")
    private Emergency emergency;

    @ManyToOne
    @JoinColumn(name = "DoseID")
    private DoseMeasure doseMeasure;

    @Column(name = "PopulationGroup", length = 100)
    private String populationGroup;

    @Column(name = "ExposureType", length = 100)
    private String exposureType;

    @Column(name = "HealthEffect", columnDefinition = "TEXT")
    private String healthEffect;
}
