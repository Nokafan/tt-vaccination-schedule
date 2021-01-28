package com.example.vaccination.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String details;
}
