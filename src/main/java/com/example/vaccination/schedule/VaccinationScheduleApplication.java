package com.example.vaccination.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EntityScan("com.example.vaccination.schedule.entity")
@EnableJpaRepositories("com.example.vaccination.schedule.repository")
@EnableTransactionManagement
@EnableWebMvc
public class VaccinationScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccinationScheduleApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
