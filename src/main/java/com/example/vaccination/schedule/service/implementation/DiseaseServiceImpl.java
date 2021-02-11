package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.dto.PeriodRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.mapper.PeriodMapper;
import com.example.vaccination.schedule.repository.DiseaseRepository;
import com.example.vaccination.schedule.service.DiseaseService;
import com.example.vaccination.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;


    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Disease save(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Disease> saveAll(Iterable<Disease> diseases) {
        return diseaseRepository.saveAll(diseases);
    }

    @Override
    public Page<Disease> getAll(Pageable pageable) {
        return diseaseRepository.findAll(pageable);
    }

    @Override
    public Disease get(Long id) {
        return diseaseRepository.findById(id)
                .orElseThrow(() -> new DataProcessingException("Not found Diseases id: " + id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        diseaseRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        diseaseRepository.deleteAllByIdIsIn(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Disease update(Long id, DiseaseRequestDto requestDto) {
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new DataProcessingException("Not found Diseases id: " + id));
        disease.setDiseaseName(requestDto.getDisease());
        disease.setVaccinationAge(getUpdatedPeriod(requestDto));
        return diseaseRepository.save(disease);
    }

    @Override
    public Page<Disease> findAllSkipped(Long id, Pageable pageable) {
        return diseaseRepository.findAllSkipped(id, pageable);
    }

    @Override
    public List<Disease> findNotDoneDiseaseByDiseaseName(String diseaseName, List<Period> period) {
        return diseaseRepository.findAllByDiseaseNameAndVaccinationAgeIsNotIn(diseaseName, period);
    }

    private Period getUpdatedPeriod(DiseaseRequestDto requestDto) {
        return Period.of(requestDto.getYears(),
                requestDto.getMonths(),
                requestDto.getDays());
    }
}
