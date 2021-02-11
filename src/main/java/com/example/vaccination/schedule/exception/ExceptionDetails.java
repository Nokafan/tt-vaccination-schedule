package com.example.vaccination.schedule.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String details;
}
