package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Period;
import java.util.List;

public interface DiseaseService extends GeneralService<Disease> {
    Disease update(Long id, DiseaseRequestDto requestDto);

    Page<Disease> findAllSkipped(Long id, Pageable pageable);

    List<Disease> findNotDoneDiseaseByDiseaseName(String diseaseName, List<Period> period);
}
