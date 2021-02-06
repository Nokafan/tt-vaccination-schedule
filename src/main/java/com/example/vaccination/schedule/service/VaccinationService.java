package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.PeriodRequestDto;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.entity.Vaccination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccinationService extends GeneralService<Vaccination> {
    Vaccination update(Long id, VaccinationRequestDto requestDto);

    Page<Vaccination> findAllByUserId(Long userId, Pageable pageable);

    Page<Vaccination> findAllSkipped(Long userId, Pageable pageable);

    Page<Vaccination> findAllByDiseaseName(Long userId, String diseaseName, Pageable pageable);

    Page<Vaccination> findAllByFuturePeriod(Long userId, PeriodRequestDto requestDto, Pageable page
    );
}
