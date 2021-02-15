package com.example.vaccination.schedule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VaccinationResponceDto {
    private Long id;
    private String vaccineName;
    private String vaccinationDateTime;
    private String diseaseName;
    private Long userId;
}
