package com.project.trialmatching.service;

import com.project.trialmatching.dto.TrialRequest;
import com.project.trialmatching.model.entity.*;
import com.project.trialmatching.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrialService {

    private final TrialRepository trialRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public Trial createTrial(TrialRequest request, String clinicianEmail) {
        User clinician = userRepository.findByEmail(clinicianEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Trial trial = Trial.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .inclusionCriteria(request.getInclusionCriteria())
                .exclusionCriteria(request.getExclusionCriteria())
                .company(company)
                .hospital(clinician.getHospital()) // trial scoped to clinician's hospital
                .build();

        return trialRepository.save(trial);
    }

    public List<Trial> getTrialsForClinician(String email) {
        User clinician = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return trialRepository.findByHospitalId(clinician.getHospital().getId());
    }
}
