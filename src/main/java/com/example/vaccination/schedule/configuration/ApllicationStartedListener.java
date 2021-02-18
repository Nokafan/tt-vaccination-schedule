package com.example.vaccination.schedule.configuration;

import static com.example.vaccination.schedule.configuration.Constants.BAR_RUB_MUM;
import static com.example.vaccination.schedule.configuration.Constants.DARNITSA;
import static com.example.vaccination.schedule.configuration.Constants.DIP_PER_TAT;
import static com.example.vaccination.schedule.configuration.Constants.DIP_TAT;
import static com.example.vaccination.schedule.configuration.Constants.HEMOFILIC;
import static com.example.vaccination.schedule.configuration.Constants.HEPATITIS_B;
import static com.example.vaccination.schedule.configuration.Constants.POLIO;
import static com.example.vaccination.schedule.configuration.Constants.TEST_I_UA;
import static com.example.vaccination.schedule.configuration.Constants.TUBERCULOSIS;

import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.service.DiseaseService;
import com.example.vaccination.schedule.service.UserService;
import com.example.vaccination.schedule.service.VaccinationService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class ApllicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {
    private final DiseaseService diseaseService;
    private final UserService userService;
    private final VaccinationService vaccinationService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApllicationStartedListener(DiseaseService diseaseService,
                                      UserService userService,
                                      VaccinationService vaccinationService,
                                      BCryptPasswordEncoder passwordEncoder) {
        this.diseaseService = diseaseService;
        this.userService = userService;
        this.vaccinationService = vaccinationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Application started");
        List<Disease> diseases = new ArrayList<>(Arrays.asList(
                Disease.builder()
                        .diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder()
                        .diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder()
                        .diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 12, 0)).build(),
                Disease.builder()
                        .diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 0, 1)).build(),
                Disease.builder()
                        .diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder()
                        .diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder()
                        .diseaseName(TUBERCULOSIS).vaccinationAge(Period.of(0, 0, 3)).build(),
                Disease.builder()
                        .diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 18, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(6, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(16, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(26, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(36, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(46, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(56, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(66, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(76, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(86, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(DIP_TAT).vaccinationAge(Period.of(96, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(0, 18, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(6, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(POLIO).vaccinationAge(Period.of(14, 0, 0)).build(),
                Disease.builder()
                        .diseaseName(BAR_RUB_MUM).vaccinationAge(Period.of(0, 12, 0)).build(),
                Disease.builder()
                        .diseaseName(BAR_RUB_MUM).vaccinationAge(Period.of(6, 0, 0)).build()));
        diseaseService.saveAll(diseases);
        log.info("Diseases saved");

        User user = User.builder()
                .dateOfBirth(LocalDate.now().minusYears(1))
                .email(TEST_I_UA)
                .name("Bob")
                .familyName("Marley")
                .password(passwordEncoder.encode("Password1"))
                .build();
        log.info("User created");
        User repositoryUser = userService.save(user);
        log.info("User saved");

        Vaccination vaccination = Vaccination.builder()
                .vaccinationDateTime(LocalDateTime.now())
                .user(repositoryUser)
                .email(TEST_I_UA)
                .vaccineName(DARNITSA)
                .disease(diseaseService.get(1L))
                .build();
        log.info("VaccinationHistory created " + vaccination.toString());

        vaccinationService.save(vaccination);
        log.info("VaccinationHistory saved");
    }
}
