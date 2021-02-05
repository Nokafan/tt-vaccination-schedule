package com.example.vaccination.schedule.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Data
public class DiseaseRequestDto {
    public static final String MESSAGE_RANGE = "Should be bigger or equals 0, and less then 100";
    public static final String MESSAGE_STRING = "Length couldn't be less then 0 and more then 100 chars";
    @NotBlank
    @Length(max = 100, message = "Disease couldn't be less then 0 and more then 100 chars")
    private String disease;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    @NotBlank
    @Length(max = 100, message = MESSAGE_STRING)
    private String vaccineName;
    private int years;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int months;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int days;
}
