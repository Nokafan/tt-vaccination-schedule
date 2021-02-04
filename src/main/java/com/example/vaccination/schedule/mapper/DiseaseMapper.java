package com.example.vaccination.schedule.mapper;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.dto.DiseaseResponseDto;
import com.example.vaccination.schedule.entity.Disease;
import org.springframework.stereotype.Component;
import org.threeten.extra.AmountFormats;

import java.time.Period;
import java.util.Locale;

@Component
public class DiseaseMapper {

    public Disease dtoToEntity(DiseaseRequestDto requestDto) {
        Period period = Period.of(requestDto.getYears(),
                requestDto.getMonths(),
                requestDto.getDays());
        return Disease.builder()
                .disease(requestDto.getDisease())
                .vaccinationAge(period)
                .build();
    }

    public DiseaseResponseDto entityToDto(Disease disease) {
        return DiseaseResponseDto.builder()
                .id(disease.getId())
                .disease(disease.getDisease())
                .vaccinationAge(formatPeriod(disease.getVaccinationAge()))
                .build();
    }

    private String formatPeriod(Period vaccinationAge) {
        return AmountFormats.wordBased(vaccinationAge, Locale.ENGLISH);
    }
}
