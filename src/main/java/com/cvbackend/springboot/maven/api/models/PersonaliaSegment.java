package com.cvbackend.springboot.maven.api.models;

import lombok.Data;
import java.util.Map;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;
//import com.cvbackend.springboot.maven.api.utils.FileUtility;
import com.cvbackend.springboot.maven.api.utils.StringUtility;

@Data
@Component
public class PersonaliaSegment {

    final static Integer MAX_FILE_SIZE = 4_194_304;
    final static String EMAIL_PATTERN = "^(.+)@(\\S+)$";
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

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateFirstName();
            ValidateLastName();
            ValidateEmail();
            ValidatePhone();
            ValidateDateOfBirth();
            ValidateGender();
            ValidateAddresses();
            ValidateZipCode();
            ValidateCity();
            ValidateCountry();
            ValidateSummary();
            ValidateProfilePicture();
            
        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

    private void ValidateFirstName() throws Exception {
        if (this.firstName == null || this.firstName.isBlank()) {
            throw new Exception("First name not supplied");
        }
    }

    private void ValidateLastName() throws Exception {
        if (this.lastName == null || this.lastName.isBlank()) {
            throw new Exception("Last name not supplied");
        }
    }

    private void ValidateEmail() throws Exception {
        if (this.email == null || this.email.isBlank()) {
            throw new Exception("Email not supplied");
        }
        if (!StringUtility.IsPatternMatching(this.email, EMAIL_PATTERN)) {
            throw new Exception("Email not valid");
        }
    }

    private void ValidatePhone() throws Exception {
        if (this.phone == null || this.phone.isBlank()) {
            throw new Exception("Phone number not supplied");
        }
        if (!StringUtility.IsPatternMatching(this.phone, PHONE_PATTERN)) {
            throw new Exception("Phone number not valid");
        }
    }

    private void ValidateDateOfBirth() throws Exception {
        if (this.dateOfBirth == null || this.dateOfBirth.isBlank()) {
            throw new Exception("Date of birth not supplied");
        }
        if (!DateTimeUtility.IsValidDateTime(this.dateOfBirth)) {
            throw new Exception("Date of birth not valid");
        }
        if (!DateTimeUtility.IsFormerDateEarlierThanLatterDate(
            DateTimeUtility.DateTimeStringToObject(this.dateOfBirth), 
            null)) {
            throw new Exception("Date of birth is not before today");
        }
    }

    private void ValidateGender() throws Exception {
        if (this.gender == null || this.gender.isBlank()) {
            throw new Exception("Gender not supplied");
        }
        if (!this.gender.equals("Female") && !this.gender.equals("Male")) {
            throw new Exception("Gender must be either 'Female' or 'Male'");
        }
    }

    private void ValidateAddresses() throws Exception {
        if (this.address == null || this.address.isBlank()) {
            throw new Exception("Address not supplied");
        }
    }

    private void ValidateZipCode() throws Exception {
        if (this.zipCode == null || this.zipCode.isBlank()) {
            throw new Exception("Zip Code not supplied");
        }
    }

    private void ValidateCity() throws Exception {
        if (this.city == null || this.city.isBlank()) {
            throw new Exception("City not supplied");
        }
    }

    private void ValidateCountry() throws Exception {
        if (this.country == null || this.country.isBlank()) {
            throw new Exception("Country not supplied");
        }
    }

    private void ValidateSummary() {
        return;
    }

    private void ValidateProfilePicture() {
        return;
    }

}
