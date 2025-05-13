package com.project.trialmatching.controller;

import com.project.trialmatching.model.mongo.PatientEhr;
import com.project.trialmatching.service.PatientEhrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ehrs")
@RequiredArgsConstructor
public class PatientEhrController {

    private final PatientEhrService ehrService;

    @PostMapping
    public PatientEhr uploadEhr(@RequestBody PatientEhr ehr) {
        return ehrService.addEhr(ehr);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<PatientEhr> getEhrs(@PathVariable UUID hospitalId) {
        return ehrService.getEhrsByHospital(hospitalId);
    }
}

