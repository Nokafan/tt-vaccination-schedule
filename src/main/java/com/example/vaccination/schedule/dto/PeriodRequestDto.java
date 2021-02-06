package com.example.vaccination.schedule.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PeriodRequestDto {
    public static final String MESSAGE_RANGE = "Should be bigger or equals 0, and less then 100";
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int years;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int months;
    @Range(min = 0, max = 100, message = MESSAGE_RANGE)
    private int days;
}
