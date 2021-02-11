package com.example.vaccination.schedule.controller;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.dto.DiseaseResponseDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.mapper.DiseaseMapper;
import com.example.vaccination.schedule.service.DiseaseService;
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
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/api/disease")
@Validated
public class DiseaseController {
    private final DiseaseService diseaseService;
    private final DiseaseMapper diseaseMapper;

    @Autowired
    public DiseaseController(DiseaseService diseaseService, DiseaseMapper diseaseMapper) {
        this.diseaseService = diseaseService;
        this.diseaseMapper = diseaseMapper;
    }

    @PostMapping
    public Disease createDisease(@Valid @RequestBody DiseaseRequestDto requestDto) {
        Disease disease = diseaseService.save(diseaseMapper.dtoToEntity(requestDto));
        log.info("Disease created id: " + disease.getId() + " name: " + disease.getDiseaseName());
        return disease;
    }

    @GetMapping("/page")
    public Page<DiseaseResponseDto> getAllDisease(Pageable pageable) {
        return diseaseService.getAll(pageable).map(diseaseMapper::entityToDto);
    }

    @GetMapping("/{id}")
    public DiseaseResponseDto getDiseaseById(@PathVariable(name = "id") Long id) {
        return diseaseMapper.entityToDto(diseaseService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteDisease(@PathVariable(name = "id") Long id) {
        diseaseService.delete(id);
    }

    @PutMapping("/{id}")
    public DiseaseResponseDto updateDisease(@PathVariable(name = "id") Long id,
                                            @Valid @RequestBody DiseaseRequestDto requestDto) {
        Disease disease = diseaseService.update(id, requestDto);
        return diseaseMapper.entityToDto(disease);
    }
}
