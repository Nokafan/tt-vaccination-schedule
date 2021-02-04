package com.example.vaccination.schedule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiseaseResponseDto {
    private Long id;
    private String disease;
    private String vaccinationAge;
}
