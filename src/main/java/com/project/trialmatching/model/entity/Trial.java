package com.project.trialmatching.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "trials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(length = 1000)
    private String inclusionCriteria;

    @Column(length = 1000)
    private String exclusionCriteria;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    @JsonIgnore // 🔁 Prevents recursive serialization
    private Hospital hospital;
}
