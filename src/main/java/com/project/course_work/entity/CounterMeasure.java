package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countermeasures")
public class CounterMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CountermeasureID")
    private Integer id;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Type", length = 50)
    private String type;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
}