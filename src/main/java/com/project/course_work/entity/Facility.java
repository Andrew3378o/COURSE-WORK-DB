package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data; // Припускаючи, що ви використовуєте Lombok @Data

@Data
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacilityID")
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Location", length = 100)
    private String location;

    @Column(name = "TechnologyType", length = 100)
    private String technologyType;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

}