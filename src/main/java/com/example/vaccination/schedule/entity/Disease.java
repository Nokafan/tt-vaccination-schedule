package com.example.vaccination.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Period;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    @NotNull(message = "Disease name can't be null")
    private String diseaseName;

    @NotNull
    @Builder.Default
    private String vaccineName = "Not specified";

    @NotNull(message = "Period can't be null")
    private Period vaccinationAge;
}
