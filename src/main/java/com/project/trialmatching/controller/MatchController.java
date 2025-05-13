package com.project.trialmatching.controller;

import com.project.trialmatching.dto.MatchResultDto;
import com.project.trialmatching.model.entity.MatchResult;
import com.project.trialmatching.service.MatchEngineService;
import com.project.trialmatching.service.MatchResultService;
import com.project.trialmatching.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchEngineService matchEngineService;
    private final MatchResultService matchResultService;
    private final JwtUtil jwtUtil;


    @PostMapping("/{ehrId}")
    public ResponseEntity<List<MatchResultDto>> match(@PathVariable String ehrId,
                                                      @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        List<MatchResultDto> results = matchEngineService.matchEhrToTrials(ehrId, email);
        return ResponseEntity.ok(results);
    }


    @GetMapping
    public List<MatchResultDto> getAllMatchesForClinician(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        return matchResultService.getMatchesForClinicianDto(email);
    }
}
