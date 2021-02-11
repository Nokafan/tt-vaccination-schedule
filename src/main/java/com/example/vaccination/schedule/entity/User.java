package com.example.vaccination.schedule.entity;

import com.example.vaccination.schedule.configuration.Constants;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
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
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "FamilyName can't be null")
    private String familyName;

    @NotNull(message = "Date of birth can't be null")
    @PastOrPresent(message = "Date of birth couldn't be in the future")
    @DateTimeFormat(pattern = Constants.PATTERN_DATE)
    private LocalDate dateOfBirth;

    @NotNull(message = "Email can't be null")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Password can't be null")
    private String password;
}
