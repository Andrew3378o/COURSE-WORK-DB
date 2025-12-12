package com.project.course_work.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "appliedcountermeasures")
public class AppliedCountermeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppliedID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EmergencyID")
    private Emergency emergency;

    @ManyToOne
    @JoinColumn(name = "CountermeasureID")
    private CounterMeasure counterMeasure;

    @Column(name = "ApplicationDate")
    private LocalDate applicationDate;
}
