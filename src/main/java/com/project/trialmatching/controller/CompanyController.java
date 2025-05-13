package com.project.trialmatching.controller;

import com.project.trialmatching.dto.CompanyRequest;
import com.project.trialmatching.model.entity.Company;
import com.project.trialmatching.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company createCompany(@RequestBody CompanyRequest request) {
        return companyService.addCompany(request);
    }

    @GetMapping
    public List<Company> listCompanies() {
        return companyService.getAllCompanies();
    }
}
