package com.project.trialmatching.repository;

import com.project.trialmatching.model.entity.Trial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrialRepository extends JpaRepository<Trial, UUID> {
    List<Trial> findByHospitalId(UUID hospitalId);
}
