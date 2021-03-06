package com.example.vaccination.schedule.controller;

import com.example.vaccination.schedule.dto.PeriodRequestDto;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.dto.VaccinationResponceDto;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.mapper.VaccinationMapper;
import com.example.vaccination.schedule.service.VaccinationService;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController

@RequestMapping("/api/vaccination")
@Validated
public class VaccinationController {
    private final VaccinationService vaccinationService;
    private final VaccinationMapper vaccinationMapper;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService,
                                 VaccinationMapper vaccinationMapper) {
        this.vaccinationService = vaccinationService;
        this.vaccinationMapper = vaccinationMapper;
    }

    @GetMapping("/{user_id}/done")
    public Page<VaccinationResponceDto> getDoneVaccination(
            @PathVariable(name = "user_id") Long id,
            Pageable pageable) {
        return vaccinationService.findAllByUserId(id, pageable)
                .map(vaccinationMapper::entityToDto);
    }

    @GetMapping("/{user_id}/skipped")
    public Page<VaccinationResponceDto> getSkippedVaccination(
            @PathVariable(name = "user_id") Long userId,
            Pageable pageable) {
        return vaccinationService.findAllSkipped(userId, pageable)
                .map(vaccinationMapper::entityToDto);
    }

    @GetMapping("{userId}/disease")
    public Page<VaccinationResponceDto> getVaccinationByDisease(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "diseaseName") String diseaseName,
            Pageable page) {
        return vaccinationService.findAllByDiseaseName(userId, diseaseName, page)
                .map(vaccinationMapper::entityToDto);
    }

    @GetMapping("{userId}/period")
    public Page<VaccinationResponceDto> getVaccinationByPeriod(
            @PathVariable(name = "userId") Long userId,
            @Valid @RequestBody PeriodRequestDto requestDto,
            Pageable page) {
        return vaccinationService.findAllByFuturePeriod(userId, requestDto, page)
                .map(vaccinationMapper::entityToDto);
    }

    @PostMapping
    public VaccinationResponceDto createVaccination(
            @Valid @RequestBody VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationMapper.dtoToEntity(requestDto);
        Vaccination savedVaccination = vaccinationService.save(vaccination);
        log.info("Vaccination id: " + savedVaccination.getId() + " saved.");
        return vaccinationMapper.entityToDto(savedVaccination);
    }

    @DeleteMapping("/{id}")
    public void deleteVaccination(@PathVariable(name = "id") Long id) {
        vaccinationService.delete(id);
        log.info("Vaccination id: " + id + " deleted.");
    }

    @PutMapping("/{id}")
    public VaccinationResponceDto updateVaccination(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationService.update(id, requestDto);
        return vaccinationMapper.entityToDto(vaccination);
    }
}
