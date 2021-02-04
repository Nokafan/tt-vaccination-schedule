package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.entity.Vaccination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccinationService extends GeneralService<Vaccination> {
    Vaccination update(Long id, VaccinationRequestDto requestDto);

    Page<Vaccination> findAllByUserId(Long id, Pageable pageable);
}
