package com.project.trialmatching.repository;

import com.project.trialmatching.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByName(String name);
}
