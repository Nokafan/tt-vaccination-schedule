package com.example.vaccination.schedule.controller;

import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.dto.VaccinationResponceDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.mapper.VaccinationMapper;
import com.example.vaccination.schedule.service.VaccinationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController("/api/vaccination")
@Validated
public class VaccinationController {
    private final VaccinationService vaccinationService;
    private final VaccinationMapper vaccinationMapper;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService, VaccinationMapper vaccinationMapper) {
        this.vaccinationService = vaccinationService;
        this.vaccinationMapper = vaccinationMapper;
    }

    @PostMapping("/{user_id}/done")
    public Page<VaccinationResponceDto> getDoneVaccination(@PathVariable(name = "user_id") Long id, Pageable pageable) {
        return vaccinationService.findAllById(id, pageable).map(vaccinationMapper::entityToDto);
    }

//    @PostMapping("/{user_id}/skipped")
//    public Page<Disease> getSkippedVaccination(@PathVariable(name = "user_id") Long id, Pageable pageable) {
//        return vaccinationService.getAllSkippedByUserId(id, pageable);
//    }

    @PostMapping
    public VaccinationResponceDto createVaccination(VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationMapper.dtoToEntity(requestDto);
        Vaccination savedVaccination = vaccinationService.save(vaccination);
        log.info("Vaccination id: " + savedVaccination.getId() + " saved.");
        return vaccinationMapper.entityToDto(savedVaccination);
    }
}
