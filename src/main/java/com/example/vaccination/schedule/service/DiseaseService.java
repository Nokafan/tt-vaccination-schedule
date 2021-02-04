package com.example.vaccination.schedule.service;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.entity.Disease;

import java.time.Period;
import java.util.List;

public interface DiseaseService extends GeneralService<Disease> {
    Disease update(Long id, DiseaseRequestDto requestDto);
//    List<Disease> findAllSkipped(Iterable<Long> ids, Period period);
}
