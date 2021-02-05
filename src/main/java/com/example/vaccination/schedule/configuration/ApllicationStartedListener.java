package com.example.vaccination.schedule.configuration;

import com.example.vaccination.schedule.entity.Disease;
import com.example.vaccination.schedule.entity.User;
import com.example.vaccination.schedule.entity.Vaccination;
import com.example.vaccination.schedule.service.UserService;
import com.example.vaccination.schedule.service.VaccinationService;
import com.example.vaccination.schedule.service.DiseaseService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j
@Component
public class ApllicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    public static final String HEPATITIS_B = "Гепатиту В";
    public static final String TEST_I_UA = "test@i.ua";
    public static final String OOO_DARNITSA = "OOO Дарниця";
    public static final String TUBERCULOSIS = "Туберкульозу";
    public static final String DIP_PER_TAT = "Дифтерії, кашлюка, правця";
    public static final String DIP_TAT = "Дифтерії, правця";
    public static final String POLIO = "Поліомієліту";
    public static final String HEMOFILIC = "Гемофільної інфекції";
    public static final String BAR_RUB_MUM = "Кору, краснухи, паротиту";
    private final DiseaseService diseaseService;
    private final UserService userService;
    private final VaccinationService vaccinationService;

    @Autowired
    public ApllicationStartedListener(DiseaseService diseaseService, UserService userService,
                                      VaccinationService vaccinationService) {
        this.diseaseService = diseaseService;
        this.userService = userService;
        this.vaccinationService = vaccinationService;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        log.info("Application started");
        List<Disease> diseases = new ArrayList<>(Arrays.asList(
                Disease.builder().diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 0, 1)).build(),
                Disease.builder().diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder().diseaseName(HEPATITIS_B).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder().diseaseName(TUBERCULOSIS).vaccinationAge(Period.of(0, 0, 3)).build(),
                Disease.builder().diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder().diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder().diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder().diseaseName(DIP_PER_TAT).vaccinationAge(Period.of(0, 18, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(6, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(16, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(26, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(36, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(46, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(56, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(66, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(76, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(86, 0, 0)).build(),
                Disease.builder().diseaseName(DIP_TAT).vaccinationAge(Period.of(96, 0, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(0, 6, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(0, 18, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(6, 0, 0)).build(),
                Disease.builder().diseaseName(POLIO).vaccinationAge(Period.of(14, 0, 0)).build(),
                Disease.builder().diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 2, 0)).build(),
                Disease.builder().diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 4, 0)).build(),
                Disease.builder().diseaseName(HEMOFILIC).vaccinationAge(Period.of(0, 12, 0)).build(),
                Disease.builder().diseaseName(BAR_RUB_MUM).vaccinationAge(Period.of(0, 12, 0)).build(),
                Disease.builder().diseaseName(BAR_RUB_MUM).vaccinationAge(Period.of(6, 0, 0)).build()));
        diseaseService.saveAll(diseases);
        log.info("Diseases saved");

        User user = User.builder()
                .dateOfBirth(LocalDate.now().minusYears(1))
                .email(TEST_I_UA)
                .name("Bob")
                .familyName("Marley")
                .password("password")
                .build();
        log.info("User created");
        User repositoryUser = userService.save(user);
        log.info("User saved");

        Vaccination vaccination = Vaccination.builder()
                .vaccinationDateTime(LocalDateTime.now())
                .user(repositoryUser)
                .email(TEST_I_UA)
                .vaccineName(OOO_DARNITSA)
                .disease(diseaseService.get(1L))
                .build();
        log.info("VaccinationHistory created " + vaccination.toString());

        vaccinationService.save(vaccination);
        log.info("VaccinationHistory saved");
    }
}
