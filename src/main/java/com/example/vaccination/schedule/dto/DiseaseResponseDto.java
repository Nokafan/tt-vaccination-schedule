package com.example.vaccination.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Period;

@Data
@Builder
public class DiseaseResponseDto {
    private Long id;
    private String disease;
    private String vaccineName;
    private Period vaccinationAge;
}
