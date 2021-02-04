package com.example.vaccination.schedule.configuration;

import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public final class Constants {
    public static final boolean TRUE = true;
    public static final String PATTERN_DATE = "dd.MM.yyyy";
    public static final String PATTERN_DATE_TIME = "dd.MM.yy HH.mm";
//    public static final DateTimeFormatter FORMATTER_DD_MM_YYYY = DateTimeFormatter.ofPattern(PATTERN_DATE);
}
