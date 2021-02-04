package com.example.vaccination.schedule.entity;

import com.example.vaccination.schedule.configuration.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String name;
    private String familyName;
    @DateTimeFormat(pattern = Constants.PATTERN_DATE)
    private LocalDate dateOfBirth;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
}
