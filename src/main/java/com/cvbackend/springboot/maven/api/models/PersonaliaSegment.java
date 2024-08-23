package com.cvbackend.springboot.maven.api.models;

import lombok.Data;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import org.javatuples.Pair;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class PersonaliaSegment {

    final static Integer MAX_FILE_SIZE = 4_194_304;
    final static String EMAIL_PATTERN = "^(.+)@(\\\\S+)$";
    final static String PHONE_PATTERN = "^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$";

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String address2;
    private String zipCode;
    private String city;
    private String country;
    private String summary;
    private Map<String, Object> profilePicture;

    private Boolean isValid;
    private String validationErrorMsg;
    private Matcher matcher;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateFirstName(this.firstName);
            ValidateLastName(this.lastName);
            ValidateEmail(this.lastName);
            ValidatePhone(this.lastName);
            ValidateDateOfBirth(this.lastName);
            ValidateGender(this.lastName);
            ValidateAddress(this.lastName);
            ValidateZipCode(this.lastName);
            ValidateCity(this.lastName);
            ValidateCountry(this.lastName);
            ValidateSummary(this.lastName);
            ValidateProfilePicture(this.profilePicture);
            
        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(isValid, validationErrorMsg);

    }

    private static boolean IsPatternMatching(String matcher, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(regexPattern).matches();
    }

    private static boolean IsValidDateTime(String dateTimeString) {
        try {
            DateTimeStringToObject(dateTimeString);
        } catch(DateTimeParseException e) {
            return false;
        } catch(DateTimeException e) {
            return false;
        }
        return true;
    }

    private static LocalDateTime DateTimeStringToObject(String dateTimeString) {
        return LocalDateTime.from(DateTimeFormatter.ISO_INSTANT.parse(dateTimeString));
    }

    private static boolean IsFormerDateEarlierThanLatterDate(LocalDateTime formerDateTime, LocalDateTime latterDateTime) {
        latterDateTime = latterDateTime != null ? latterDateTime: LocalDateTime.now(); // Current time is default
        LocalDate formerDate = LocalDate.from(formerDateTime);
        LocalDate latterDate = LocalDate.from(latterDateTime);
        return formerDate.isBefore(latterDate);
    }

    private static void ValidateFirstName(String firstName) throws Exception {
        if (firstName == null) {
            throw new Exception("First name not supplied");
        }
    }

    private static void ValidateLastName(String lastName) throws Exception {
        if (lastName == null) {
            throw new Exception("Last name not supplied");
        }
    }

    private static void ValidateEmail(String email) throws Exception {
        if (email == null) {
            throw new Exception("Email not supplied");
        }
        if (!IsPatternMatching(email, EMAIL_PATTERN)) {
            throw new Exception("Email not valid");
        }
    }

    private static void ValidatePhone(String phone) throws Exception {
        if (phone == null) {
            throw new Exception("Phone number not supplied");
        }
        if (!IsPatternMatching(phone, PHONE_PATTERN)) {
            throw new Exception("Phone number not valid");
        }
    }

    private static void ValidateDateOfBirth(String dateOfBirth) throws Exception {
        if (dateOfBirth == null) {
            throw new Exception("Date of birth not supplied");
        }
        if (!IsValidDateTime(dateOfBirth)) {
            throw new Exception("Date of birth not valid");
        }
        if (!IsFormerDateEarlierThanLatterDate(DateTimeStringToObject(dateOfBirth), null)) {
            throw new Exception("Date of birth is not before today");
        }
    }

    private static void ValidateGender(String gender) throws Exception {
        if (gender == null) {
            throw new Exception("Gender not supplied");
        }
        if (gender != "Female" || gender != "Male") {
            throw new Exception("Gender must be either 'Female' or 'Male'");
        }
    }

    private static void ValidateAddress(String address) throws Exception {
        if (address == null) {
            throw new Exception("Address not supplied");
        }
    }

    private static void ValidateZipCode(String zipCode) throws Exception {
        if (zipCode == null) {
            throw new Exception("Zip Code not supplied");
        }
    }

    private static void ValidateCity(String city) throws Exception {
        if (city == null) {
            throw new Exception("City not supplied");
        }
    }

    private static void ValidateCountry(String country) throws Exception {
        if (country == null) {
            throw new Exception("Country not supplied");
        }
    }

    private static void ValidateSummary(String summary) {}

    private static void ValidateProfilePicture(Map<String, Object> profilePciture) throws Exception {}

}
