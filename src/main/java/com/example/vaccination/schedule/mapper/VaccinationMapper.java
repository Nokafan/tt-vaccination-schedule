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
                .id(vaccination.getId())
                .vaccinationDate(vaccination.getVaccinationDateTime())
                .diseaseId(vaccination.getDisease().getId())
                .userId(vaccination.getUser().getId())
                .vaccineName(vaccination.getVaccineName())
                .build();
    }

    public Vaccination dtoToEntity(VaccinationRequestDto requestDto) {
        return Vaccination.builder()
                .email(requestDto.getEmail())
                .vaccineName(requestDto.getVaccineName())
                .user(userService.get(requestDto.getUserId()))
                .disease(diseaseService.get(requestDto.getDiseaseId()))
                .vaccinationDateTime(LocalDateTime.parse(requestDto.getVaccinationDateTime(),
                        DateTimeFormatter.ofPattern(Constants.PATTERN_DATE_TIME)))
                .build();
    }
}
