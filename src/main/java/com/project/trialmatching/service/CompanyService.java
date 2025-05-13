package com.project.trialmatching.service;

import com.project.trialmatching.dto.CompanyRequest;
import com.project.trialmatching.model.entity.Company;
import com.project.trialmatching.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company addCompany(CompanyRequest request) {
        if (companyRepository.existsByName(request.getName())) {
            throw new RuntimeException("Company already exists");
        }

        Company company = Company.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
