package com.example.vaccination.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EntityScan("com.example.vaccination.schedule.entity")
@EnableJpaRepositories("com.example.vaccination.schedule.repository")
public class VaccinationScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccinationScheduleApplication.class, args);
    }

}
