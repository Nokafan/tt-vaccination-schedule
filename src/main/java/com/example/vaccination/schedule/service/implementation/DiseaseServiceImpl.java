package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.repository.DiseaseRepository;
import com.example.vaccination.schedule.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository repository;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Disease save(Disease disease) {
        return repository.save(disease);
    }

    @Override
    public List<Disease> saveAll(Iterable<Disease> diseases) {
        return repository.saveAll(diseases);
    }

    @Override
    public Page<Disease> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Disease get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataProcessingException("Not found Diseases id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Disease> findAllById(Long id, Pageable pageable) {
        return repository.findAllById(id, pageable);
    }

    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        repository.deleteAllByIdIsIn(ids);
    }

    @Override
    public Disease update(Long id, DiseaseRequestDto requestDto) {
        Disease disease = repository.findById(id)
                .orElseThrow(() -> new DataProcessingException("Not found Diseases id: " + id));
        Period updatedPeriod = Period.of(requestDto.getYears(),
                requestDto.getMonths(),
                requestDto.getDays());
        disease.setDisease(requestDto.getDisease());
        disease.setVaccinationAge(updatedPeriod);
        return repository.save(disease);
    }

//    @Override
//    public List<Disease> findAllSkipped(Iterable<Long> ids, Period period) {
//        return repository.findAllByIdIsNotInAndVaccinationAgeIsLessThan(ids, period);
//    }
}
