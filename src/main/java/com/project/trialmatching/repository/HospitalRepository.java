package com.project.trialmatching.repository;

import com.project.trialmatching.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}
