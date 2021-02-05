package com.example.vaccination.schedule.entity;

import com.example.vaccination.schedule.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String familyName;

    @PastOrPresent(message = "Date of birth couldn't be in the future")
    @DateTimeFormat(pattern = Constants.PATTERN_DATE)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;
}
