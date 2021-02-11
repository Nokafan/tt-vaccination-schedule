package com.example.vaccination.schedule.configuration;

import lombok.Data;

@Data
public class Constants {
    public static final int ONE = 1;
    public static final boolean TRUE = true;
    public static final String PATTERN_DATE = "dd.MM.yyyy";
    public static final String PATTERN_DATE_TIME = "dd.MM.yyyy HH:mm";
    public static final String HEPATITIS_B = "Гепатиту В";
    public static final String TEST_I_UA = "test@i.ua";
    public static final String DARNITSA = "OOO Дарниця";
    public static final String TUBERCULOSIS = "Туберкульозу";
    public static final String DIP_PER_TAT = "Дифтерії, кашлюка, правця";
    public static final String DIP_TAT = "Дифтерії, правця";
    public static final String POLIO = "Поліомієліту";
    public static final String HEMOFILIC = "Гемофільної інфекції";
    public static final String BAR_RUB_MUM = "Кору, краснухи, паротиту";
    public static final String MESSAGE_RANGE = "Should be bigger or equals 0, and less then 100";
    public static final String MESSAGE_STRING =
            "Length couldn't be less then 0 and more then 100 chars";
}
