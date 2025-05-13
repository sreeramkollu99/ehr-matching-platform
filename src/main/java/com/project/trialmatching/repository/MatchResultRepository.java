package com.project.trialmatching.repository;

import com.project.trialmatching.model.entity.MatchResult;
import com.project.trialmatching.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchResultRepository extends JpaRepository<MatchResult, UUID> {
    List<MatchResult> findByClinician(User clinician);

}
