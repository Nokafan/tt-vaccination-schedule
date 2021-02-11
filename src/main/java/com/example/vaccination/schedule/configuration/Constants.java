package com.example.vaccination.schedule.configuration;

import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public class Constants {
    public static final int ONE = 1;
    public static final boolean TRUE = true;
    public static final String PATTERN_DATE = "dd.MM.yyyy";
    public static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm";

}
