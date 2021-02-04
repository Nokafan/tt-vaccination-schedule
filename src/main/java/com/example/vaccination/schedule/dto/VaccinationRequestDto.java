package com.example.vaccination.schedule.dto;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.validator.ValidEmail;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
public class VaccinationRequestDto {
    @ValidEmail
    private String email;
    @NotBlank
    private String vaccineName;
    @DateTimeFormat(pattern = Constants.PATTERN_DATE_TIME)
    private String vaccinationDateTime;
    @Range
    private Long userId;
    @Range
    private Long diseaseId;
}
