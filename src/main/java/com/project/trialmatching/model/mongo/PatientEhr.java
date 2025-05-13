package com.project.trialmatching.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document(collection = "patient_ehrs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEhr {

    @Id
    private String id;

    private String patientId;

    private UUID hospitalId; // to link with relational hospital

    private Map<String, Object> data; // flexible JSON structure

    private String sourceType; // "UPLOAD" or "API"
}
