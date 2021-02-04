package com.example.vaccination.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String disease;
    private Period vaccinationAge;
}
