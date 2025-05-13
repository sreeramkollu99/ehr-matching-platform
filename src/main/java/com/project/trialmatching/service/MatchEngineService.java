package com.project.trialmatching.service;

import com.project.trialmatching.dto.MatchResultDto;
import com.project.trialmatching.model.entity.*;
import com.project.trialmatching.model.mongo.PatientEhr;
import com.project.trialmatching.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchEngineService {

    private final PatientEhrRepository ehrRepository;
    private final TrialRepository trialRepository;
    private final MatchResultRepository matchResultRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;

    public List<MatchResultDto> matchEhrToTrials(String ehrId, String clinicianEmail) {
        PatientEhr ehr = ehrRepository.findById(ehrId)
                .orElseThrow(() -> new RuntimeException("EHR not found"));

        User clinician = userRepository.findByEmail(clinicianEmail)
                .orElseThrow(() -> new RuntimeException("Clinician not found"));

        UUID hospitalId = clinician.getHospital().getId();
        List<Trial> trials = trialRepository.findByHospitalId(hospitalId);

        return trials.stream().map(trial -> {
            boolean matched = simpleMatch(ehr, trial);

            MatchResult result = MatchResult.builder()
                    .patientId(ehr.getPatientId())
                    .trial(trial)
                    .hospital(clinician.getHospital())
                    .clinician(clinician)
                    .isMatched(matched)
                    .matchedAt(LocalDateTime.now())
                    .build();

            matchResultRepository.save(result);

            return MatchResultDto.from(result);
        }).toList();
    }



    private boolean simpleMatch(PatientEhr ehr, Trial trial) {
        Map<String, Object> ehrData = ehr.getData();
        if (ehrData != null && ehrData.containsKey("conditions")) {
            List<String> conditions = (List<String>) ehrData.get("conditions");
            return conditions.contains("diabetes") && trial.getInclusionCriteria().toLowerCase().contains("diabetes");
        }
        return false;
    }
}
