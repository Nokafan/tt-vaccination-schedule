package com.example.vaccination.schedule.entity;

import com.example.vaccination.schedule.configuration.Constants;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.List;

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
    @JsonProperty("username")
    private String email;

    @NotNull(message = "Password can't be null")
    @JsonProperty("password")
    private String password;

    @JsonCreator
    public User(@JsonProperty("username") @NotNull(message = "Email can't be null") String email,
                @JsonProperty("password") @NotNull(message = "Password can't be null") String password) {
        this.email = email;
        this.password = password;
    }
}
