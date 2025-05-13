package com.project.trialmatching.service;

import com.project.trialmatching.dto.MatchResultDto;
import com.project.trialmatching.model.entity.MatchResult;
import com.project.trialmatching.model.entity.User;
import com.project.trialmatching.repository.MatchResultRepository;
import com.project.trialmatching.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchResultService {

    private final MatchResultRepository matchResultRepository;
    private final UserRepository userRepository;

    public List<MatchResult> getMatchesForClinician(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return matchResultRepository.findByClinician(user);
    }
    public List<MatchResultDto> getMatchesForClinicianDto(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Clinician not found"));

        return matchResultRepository.findByClinician(user).stream()
                .map(MatchResultDto::from)
                .toList();
    }
}
