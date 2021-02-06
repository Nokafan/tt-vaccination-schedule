package com.example.vaccination.schedule.mapper;

import com.example.vaccination.schedule.dto.PeriodRequestDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Period;

@Data
@Component
public class PeriodMapper {
    public Period dtoToEntity(PeriodRequestDto requestDto) {
        return Period.of(
                requestDto.getYears(),
                requestDto.getMonths(),
                requestDto.getDays());
    }
}
