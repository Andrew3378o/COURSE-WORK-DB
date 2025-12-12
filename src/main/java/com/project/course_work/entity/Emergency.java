package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "emergencies")
public class Emergency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmergencyID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FacilityID")
    private Facility facility;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "Scale", length = 50)
    private String scale;

    @Column(name = "CommunalType", length = 50)
    private String communalType;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
}
