package com.cvbackend.springboot.maven.api.utils;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class DateTimeUtility {

    public static boolean IsValidDateTime(String dateTimeString) {
        try {
            DateTimeStringToObject(dateTimeString);
        } catch(DateTimeParseException e) {
            return false;
        } catch(DateTimeException e) {
            return false;
        }
        return true;
    }

    public static LocalDateTime DateTimeStringToObject(String dateTimeString) {
        return Instant.parse(dateTimeString).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static boolean IsFormerDateEarlierThanLatterDate(LocalDateTime formerDateTime, LocalDateTime latterDateTime) {
        latterDateTime = latterDateTime != null ? latterDateTime: LocalDateTime.now(); // Current time is default
        LocalDate formerDate = LocalDate.from(formerDateTime);
        LocalDate latterDate = LocalDate.from(latterDateTime);
        return formerDate.isBefore(latterDate);
    }
    
}