package com.project.trialmatching.controller;

import com.project.trialmatching.dto.TrialRequest;
import com.project.trialmatching.model.entity.Trial;
import com.project.trialmatching.service.TrialService;
import com.project.trialmatching.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trials")
@RequiredArgsConstructor
public class TrialController {

    private final TrialService trialService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Trial> createTrial(@RequestBody TrialRequest request,
                                             @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        Trial created = trialService.createTrial(request, email);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Trial> getTrials(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        return trialService.getTrialsForClinician(email);
    }
}
