package com.example.vaccination.schedule.service.implementation;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.dto.VaccinationRequestDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j
@Service
public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository repository;
    private final UserService userService;
    private final DiseaseService diseaseService;

    @Autowired
    public VaccinationServiceImpl(VaccinationRepository repository,
                                  @Lazy UserService userService,
                                  @Lazy DiseaseService diseaseService) {
        this.repository = repository;
        this.userService = userService;
        this.diseaseService = diseaseService;
    }


    @Transactional
    @Override
    public Vaccination save(Vaccination vaccination) {
        return repository.save(vaccination);
    }

    @Transactional
    @Override
    public List<Vaccination> saveAll(Iterable<Vaccination> vaccinations) {
        return repository.saveAll(vaccinations);
    }

    @Override
    public Page<Vaccination> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Vaccination get(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found VaccinationHistory with id: " + id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Vaccination> findAllById(Long id, Pageable pageable) {
        return repository.findAllById(id, pageable);
    }

    @Transactional
    @Override
    public void deleteAllByIds(Iterable<Long> ids) {
        repository.deleteAllByIdIsIn(ids);
    }

    @Transactional
    @Override
    public Vaccination update(Long id, VaccinationRequestDto requestDto) {
        Vaccination vaccination = repository.findById(id).orElseThrow(() ->
                new DataProcessingException("Not found VaccinationHistory with id: " + id));
        vaccination.setEmail(requestDto.getEmail());
        vaccination.setVaccineName(requestDto.getVaccineName());
        vaccination.setVaccinationDateTime(LocalDateTime.parse(requestDto.getVaccinationDateTime(),
                DateTimeFormatter.ofPattern(Constants.PATTERN_DATE_TIME)));
        vaccination.setUser(userService.get(requestDto.getUserId()));
        vaccination.setDisease(diseaseService.get(requestDto.getDiseaseId()));
        repository.save(vaccination);
        log.info("Vaccination id: " + id + " has been updated.");
        return vaccination;
    }

    //    @Override
//    public List<Vaccination> getAllByUserId(Long id) {
//        return repository.findAllByUser_Id(id);
//    }

//    @Override
//    public Page<Disease> getAllSkippedByUserId(Long id, Pageable pageable) {
//        User user = userService.get(id);
//        Period period = Period.between(user.getDateOfBirth(), LocalDate.now());
//        List<Long> diseaseIds = getAllByUserId(id)
//                .stream()
//                .map(v -> v.getDisease().getId())
//                .collect(Collectors.toList());
//        List<Disease> diseaseList = diseaseService.findAllSkipped(diseaseIds, period);
////        List<VaccinationResponceDto> vaccinations = diseaseList.stream()
////                .map(x -> VaccinationResponceDto.builder()
////                        .vaccineName("No vaccine")
////                        .vaccineName(x.getDisease())
////                        .vaccinationDate((user.getDateOfBirth().plus(period)).atTime(LocalTime.now()))
////                        .build())
////                .collect(Collectors.toList());
////        return new PageImpl<VaccinationResponceDto>(vaccinations, pageable, vaccinations.size());
//        return new PageImpl<Disease>(diseaseList, pageable, diseaseList.size());
////        return diseaseList;
////        return null;
//    }
}
