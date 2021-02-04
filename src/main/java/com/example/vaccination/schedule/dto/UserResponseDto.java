package com.example.vaccination.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String familyName;
    private LocalDate dateOfBirth;
    private String email;
}
