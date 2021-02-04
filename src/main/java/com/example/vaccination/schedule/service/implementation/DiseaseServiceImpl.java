package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.dto.DiseaseRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.repository.DiseaseRepository;
import com.example.vaccination.schedule.service.DiseaseService;
import com.example.vaccination.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository repository;
    private final UserService userService;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository repository,
                              UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Disease save(Disease disease) {
        return repository.save(disease);
    }

    @Transactional
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

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        repository.deleteAllByIdIsIn(ids);
    }

    @Transactional
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

    @Override
    public Page<Disease> findAllSkipped(Long id, Pageable pageable) {
        User user = userService.get(id);
        LocalDate dateOfBirth = user.getDateOfBirth();
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return repository.findAllSkipped(id, period, pageable);
    }
}
