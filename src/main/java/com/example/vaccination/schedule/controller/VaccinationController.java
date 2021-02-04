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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j
@RestController
@RequestMapping("/api/vaccination")
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
    public VaccinationResponceDto createVaccination(@Valid @RequestBody VaccinationRequestDto requestDto) {
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
    public VaccinationResponceDto updateVaccination(@PathVariable(name = "id") Long id,
                                                    @Valid @RequestBody VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationService.update(id, requestDto);
        return vaccinationMapper.entityToDto(vaccination);
    }
}
