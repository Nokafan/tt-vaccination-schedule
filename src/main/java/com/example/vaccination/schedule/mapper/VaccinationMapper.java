package com.example.vaccination.schedule.mapper;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.dto.VaccinationResponceDto;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.service.DiseaseService;
import com.example.vaccination.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class VaccinationMapper {
    private final UserService userService;
    private final DiseaseService diseaseService;

    @Autowired
    public VaccinationMapper(UserService userService, DiseaseService diseaseService) {
        this.userService = userService;
        this.diseaseService = diseaseService;
    }

    public VaccinationResponceDto entityToDto(Vaccination vaccination) {
        return VaccinationResponceDto.builder()
                .vaccinationDate(vaccination.getVaccinationDateTime())
                .disease(vaccination.getDisease())
                .vaccineName(vaccination.getVaccineName())
                .build();
    }

    public Vaccination dtoToEntity(VaccinationRequestDto requestDto) {
        return Vaccination.builder()
                .email(requestDto.getEmail())
                .vaccineName(requestDto.getVaccineName())
                .vaccinationDateTime(LocalDateTime.parse(requestDto.getVaccinationDateTime(),
                        DateTimeFormatter.ofPattern(Constants.PATTERN_DATE_TIME)))
                .build();
    }
}
