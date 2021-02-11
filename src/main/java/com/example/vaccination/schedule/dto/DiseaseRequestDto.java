package com.example.vaccination.schedule.dto;

import static com.example.vaccination.schedule.configuration.Constants.MESSAGE_RANGE;
import static com.example.vaccination.schedule.configuration.Constants.MESSAGE_STRING;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class DiseaseRequestDto {
    @NotBlank
    @Length(max = 100, message = "Disease couldn't be less then 0 and more then 100 chars")
    private String disease;
    @NotBlank(message = "Vaccine name should be not blank")
    @Length(max = 100, message = MESSAGE_STRING)
    private String vaccineName;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int years;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int months;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int days;
}
