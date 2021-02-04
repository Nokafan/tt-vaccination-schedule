package com.example.vaccination.schedule.dto;

import com.example.vaccination.schedule.entity.Disease;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class VaccinationResponceDto {
    private String vaccineName;
    private LocalDateTime vaccinationDate;
    private Disease disease;
}
