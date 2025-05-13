package com.project.trialmatching.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TrialRequest {
    private String title;
    private String description;
    private String inclusionCriteria;
    private String exclusionCriteria;
    private UUID companyId;  // ID of the company running the trial
}
