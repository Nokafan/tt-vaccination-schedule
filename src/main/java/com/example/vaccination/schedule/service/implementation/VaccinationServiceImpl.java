package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.repository.VaccinationRepository;
import com.example.vaccination.schedule.service.DiseaseService;
import com.example.vaccination.schedule.service.UserService;
import com.example.vaccination.schedule.service.VaccinationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Log4j
@Service
public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository vaccinationRepository;
    private final UserService userService;
    private final DiseaseService diseaseService;

    @Autowired
    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository,
                                  @Lazy UserService userService,
                                  @Lazy DiseaseService diseaseService) {
        this.vaccinationRepository = vaccinationRepository;
        this.userService = userService;
        this.diseaseService = diseaseService;
    }


    @Transactional
    @Override
    public Vaccination save(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    @Transactional
    @Override
    public List<Vaccination> saveAll(Iterable<Vaccination> vaccinations) {
        return vaccinationRepository.saveAll(vaccinations);
    }

    @Override
    public Page<Vaccination> getAll(Pageable pageable) {
        return vaccinationRepository.findAll(pageable);
    }

    @Override
    public Vaccination get(Long id) {
        return vaccinationRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found VaccinationHistory with id: " + id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        vaccinationRepository.deleteById(id);
    }

    @Override
    public Page<Vaccination> findAllByUserId(Long userId, Pageable pageable) {
        return vaccinationRepository.findAllByUser_Id(userId, pageable);
    }

    @Override
    public Page<Vaccination> findAllSkipped(Long userId, Pageable pageable) {
        User user = userService.get(userId);
        return diseaseService.findAllSkipped(userId, pageable).map(disease -> getVaccination(user, disease));
    }

    private Vaccination getVaccination(User user, Disease disease) {
        return Vaccination.builder()
                .vaccineName(disease.getVaccineName())
                .disease(disease)
                .user(user)
                .vaccinationDateTime(user.getDateOfBirth().plus(disease.getVaccinationAge()).atStartOfDay())
                .build();
    }

    @Transactional
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        vaccinationRepository.deleteAllByIdIsIn(ids);
    }

    @Transactional
    @Override
    public Vaccination update(Long id, VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found VaccinationHistory with id: " + id));
        vaccination.setEmail(requestDto.getEmail());
        vaccination.setVaccineName(requestDto.getVaccineName());
        vaccination.setVaccinationDateTime(LocalDateTime.parse(requestDto.getVaccinationDateTime(),
                DateTimeFormatter.ofPattern(Constants.PATTERN_DATE_TIME)));
        vaccination.setUser(userService.get(requestDto.getUserId()));
        vaccination.setDisease(diseaseService.get(requestDto.getDiseaseId()));
        vaccinationRepository.save(vaccination);
        log.info("Vaccination id: " + id + " has been updated.");
        return vaccination;
    }

    @Override
    public Page<Vaccination> findAllByDiseaseName(Long userId, String diseaseName, Pageable pageable) {
        User user = userService.get(userId);
        List<Vaccination> vaccinations =
                vaccinationRepository.findAllByUser_IdAndDisease_DiseaseName(userId, diseaseName);
        List<Period> periods = vaccinations.stream()
                .map(x -> x.getDisease().getVaccinationAge())
                .collect(Collectors.toList());
        List<Vaccination> notDoneVaccinations = diseaseService.findNotDoneDiseaseByDiseaseName(diseaseName, periods)
                .stream()
                .map(disease -> getVaccination(user, disease))
                .collect(Collectors.toList());
        vaccinations.addAll(notDoneVaccinations);
        return new PageImpl<>(vaccinations, pageable, vaccinations.size());
    }
}
