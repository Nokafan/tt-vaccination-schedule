package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.PeriodRequestDto;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.exception.DataProcessingException;
import com.example.vaccination.schedule.mapper.PeriodMapper;
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
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository vaccinationRepository;
    private final UserService userService;
    private final DiseaseService diseaseService;
    private final PeriodMapper periodMapper;

    @Autowired
    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository,
                                  @Lazy UserService userService,
                                  @Lazy DiseaseService diseaseService, PeriodMapper periodMapper) {
        this.vaccinationRepository = vaccinationRepository;
        this.userService = userService;
        this.diseaseService = diseaseService;
        this.periodMapper = periodMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Vaccination save(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
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
        List<Vaccination> vaccinationList = diseaseService.findAllSkipped(userId, pageable)
                .map(disease -> getVaccination(user, disease))
                .filter(vaccination -> vaccination.getVaccinationDateTime()
                        .isBefore(LocalDateTime.now().plusDays(Constants.ONE)))
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(vaccinationList, pageable, vaccinationList.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        vaccinationRepository.deleteAllByIdIsIn(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Vaccination update(Long id, VaccinationRequestDto requestDto) {
        Vaccination vaccination = vaccinationRepository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found VaccinationHistory with id: " + id));
        vaccinationRepository.save(updateVaccination(requestDto, vaccination));
        log.info("Vaccination id: " + id + " has been updated.");
        return vaccination;
    }

    @Override
    public Page<Vaccination> findAllByDiseaseName(Long userId, String diseaseName, Pageable pageable) {
        User user = userService.get(userId);
        List<Vaccination> vaccinations =
                vaccinationRepository.findAllByUser_IdAndDisease_DiseaseName(userId, diseaseName);
        vaccinations.addAll(getNotDoneVaccinations(diseaseName, user, vaccinations));
        return new PageImpl<>(vaccinations, pageable, vaccinations.size());
    }

    @Override
    public Page<Vaccination> findAllByFuturePeriod(Long userId, PeriodRequestDto requestDto, Pageable page) {
        User user = userService.get(userId);
        Period periodToSearch = periodMapper.dtoToEntity(requestDto);
        LocalDateTime endTime = getLocalDateTimeFromPeriod(periodToSearch);
        List<Vaccination> vaccinations = diseaseService
                .getAll(page).map(disease -> getVaccination(user, disease)).getContent()
                .stream()
                .filter(vaccination -> vaccination.getVaccinationDateTime().isAfter(LocalDateTime.now()))
                .filter(vaccination -> vaccination.getVaccinationDateTime().isBefore(endTime))
                .collect(Collectors.toList());
        return new PageImpl<>(vaccinations, page, vaccinations.size());
    }


    private List<Vaccination> getNotDoneVaccinations(String diseaseName, User user, List<Vaccination> vaccinations) {
        List<Period> periods = vaccinations.stream()
                .map(x -> x.getDisease().getVaccinationAge())
                .collect(Collectors.toList());
        return diseaseService.findNotDoneDiseaseByDiseaseName(diseaseName, periods)
                .stream()
                .map(disease -> getVaccination(user, disease))
                .collect(Collectors.toList());
    }

    private LocalDateTime getLocalDateTimeFromPeriod(Period periodToSearch) {
        return LocalDateTime.now()
                .plusYears(periodToSearch.getYears()).plusMonths(periodToSearch.getMonths()).plusDays(periodToSearch.getDays());
    }

    private Vaccination getVaccination(User user, Disease disease) {
        return Vaccination.builder()
                .vaccineName(disease.getVaccineName())
                .disease(disease)
                .user(user)
                .vaccinationDateTime(user.getDateOfBirth().plus(disease.getVaccinationAge()).atStartOfDay())
                .build();
    }

    private Vaccination updateVaccination(VaccinationRequestDto requestDto, Vaccination vaccination) {
        vaccination.setEmail(requestDto.getEmail());
        vaccination.setVaccineName(requestDto.getVaccineName());
        vaccination.setVaccinationDateTime(LocalDateTime.parse(requestDto.getVaccinationDateTime(),
                DateTimeFormatter.ofPattern(Constants.PATTERN_DATE_TIME)));
        vaccination.setUser(userService.get(requestDto.getUserId()));
        vaccination.setDisease(diseaseService.get(requestDto.getDiseaseId()));
        return vaccination;
    }
}
