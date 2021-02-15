package com.example.vaccination.schedule.dto;

import static com.example.vaccination.schedule.configuration.Constants.PATTERN_DATE_TIME;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class VaccinationRequestDto {
    @NotBlank
    private String vaccineName;
    @DateTimeFormat(pattern = PATTERN_DATE_TIME)
    private String vaccinationDateTime;
    @Range
    private Long userId;
    @Range
    private Long diseaseId;
}
