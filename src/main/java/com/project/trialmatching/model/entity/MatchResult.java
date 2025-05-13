package com.project.trialmatching.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "match_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String patientId;

    @ManyToOne
    @JoinColumn(name = "trial_id")
    private Trial trial;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "clinician_id", nullable = false)
    private User clinician;

    private boolean isMatched;

    private LocalDateTime matchedAt;
}
