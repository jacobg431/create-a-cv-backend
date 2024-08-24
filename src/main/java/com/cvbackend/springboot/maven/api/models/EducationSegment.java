package com.cvbackend.springboot.maven.api.models;

import org.javatuples.Pair;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;

import lombok.Data;

@Data
public class EducationSegment {
    private String school;
    private String degree;
    private String startDate;
    private String endDate;
    private Boolean isStudying;

    private int instanceIndex;
    private Boolean isValid;
    private String validationErrorMsg;

    public Pair<Boolean, String> Validate(int instanceIndex) {

        this.instanceIndex = instanceIndex;
        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateSchool();
            ValidateDegree();
            ValidateEducationDates();

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

    private void ValidateSchool() throws Exception {
        if (this.school == null) {
            throw new Exception("School name not supplied");
        }
    }

    private void ValidateDegree() {
        return;
    }

    private void ValidateEducationDates() throws Exception {

        if (this.startDate == null) {
            throw new Exception("Start date not supplied for education instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsValidDateTime(this.startDate)) {
            throw new Exception("Start date not valid for education instance " + this.instanceIndex);
        }
        if (this.endDate == null && this.isStudying) {
            return;
        }
        if (this.endDate == null && !this.isStudying) {
            throw new Exception("End date not supplied for education instance " + this.instanceIndex);
        }
        if (!this.isStudying && !DateTimeUtility.IsValidDateTime(this.endDate)) {
            throw new Exception("End date not valid for education instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsFormerDateEarlierThanLatterDate(
            DateTimeUtility.DateTimeStringToObject(this.startDate), 
            DateTimeUtility.DateTimeStringToObject(this.endDate)
            )) {
            throw new Exception("Start date is not before end date");
        }

    }

}
