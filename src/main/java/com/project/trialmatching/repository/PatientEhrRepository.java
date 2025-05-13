package com.project.trialmatching.repository;

import com.project.trialmatching.model.mongo.PatientEhr;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PatientEhrRepository extends MongoRepository<PatientEhr, String> {
    List<PatientEhr> findByHospitalId(UUID hospitalId);
}
