package com.project.trialmatching.service;

import com.project.trialmatching.model.mongo.PatientEhr;
import com.project.trialmatching.repository.PatientEhrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientEhrService {

    private final PatientEhrRepository ehrRepository;

    public PatientEhr addEhr(PatientEhr ehr) {
        return ehrRepository.save(ehr);
    }

    public List<PatientEhr> getEhrsByHospital(UUID hospitalId) {
        return ehrRepository.findByHospitalId(hospitalId);
    }
}
