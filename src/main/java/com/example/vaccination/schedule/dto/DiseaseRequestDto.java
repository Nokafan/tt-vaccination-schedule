package com.example.vaccination.schedule.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Data
public class DiseaseRequestDto {
    public static final String MESSAGE = "Should be bigger or equals 0, and less then 100";
    @NotBlank
    @Length(max = 100, message = "Disease couldn't be less then 0 and more then 100 chars")
    private String disease;
    @Range(min = 0, max = 100, message = MESSAGE)
    private int years;
    @Range(min = 0, max = 100, message = MESSAGE)
    private int months;
    @Range(min = 0, max = 100, message = MESSAGE)
    private int days;
}
