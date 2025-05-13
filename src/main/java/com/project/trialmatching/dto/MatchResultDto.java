package com.project.trialmatching.dto;

import com.project.trialmatching.model.entity.MatchResult;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResultDto {

    private UUID matchId;
    private String patientId;

    private UUID trialId;
    private String trialTitle;
    private String trialDescription;
    private String inclusionCriteria;
    private String exclusionCriteria;

    private boolean matched;
    private LocalDateTime matchedAt;

    private UUID clinicianId;
    private String clinicianName;
    private String clinicianEmail;

    private UUID hospitalId;
    private String hospitalName;

    private UUID companyId;
    private String companyName;

    public static MatchResultDto from(MatchResult result) {
        return MatchResultDto.builder()
                .matchId(result.getId())
                .patientId(result.getPatientId())

                .trialId(result.getTrial().getId())
                .trialTitle(result.getTrial().getTitle())
                .trialDescription(result.getTrial().getDescription())
                .inclusionCriteria(result.getTrial().getInclusionCriteria())
                .exclusionCriteria(result.getTrial().getExclusionCriteria())

                .matched(result.isMatched())
                .matchedAt(result.getMatchedAt())

                .clinicianId(result.getClinician().getId())
                .clinicianName(result.getClinician().getName())
                .clinicianEmail(result.getClinician().getEmail())

                .hospitalId(result.getHospital().getId())
                .hospitalName(result.getHospital().getName())

                .companyId(result.getTrial().getCompany().getId())
                .companyName(result.getTrial().getCompany().getName())

                .build();
    }
}
