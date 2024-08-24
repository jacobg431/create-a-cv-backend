package com.cvbackend.springboot.maven.api.models;

import org.javatuples.Pair;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;

import lombok.Data;

@Data
public class ExperienceSegment {
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    private Boolean isWorking;
    private String description;

    private int instanceIndex;
    private Boolean isValid;
    private String validationErrorMsg;

    public Pair<Boolean, String> Validate(int instanceIndex) {

        this.instanceIndex = instanceIndex;
        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateCompany();
            ValidatePosition();
            ValidateExperienceDates();
            ValidateDescription();

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

    private void ValidateCompany() throws Exception {
        if (this.company == null) {
            throw new Exception("Company name not supplied for experience instance " + this.instanceIndex);
        }
    }

    private void ValidatePosition() throws Exception {
        if (this.position == null) {
            throw new Exception("Position not supplied for experience instance " + this.instanceIndex);
        }
    }

    private void ValidateExperienceDates() throws Exception {

        if (this.startDate == null) {
            throw new Exception("Start date not supplied for experience instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsValidDateTime(startDate)) {
            throw new Exception("Start date not valid for experience instance " + this.instanceIndex);
        }
        if (this.endDate == null && this.isWorking) {
            return;
        }
        if (this.endDate == null && !this.isWorking) {
            throw new Exception("End date not supplied for experience instance " + this.instanceIndex);
        }
        if (!this.isWorking && !DateTimeUtility.IsValidDateTime(this.endDate)) {
            throw new Exception("End date not valid for experience instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsFormerDateEarlierThanLatterDate(
            DateTimeUtility.DateTimeStringToObject(this.startDate), 
            DateTimeUtility.DateTimeStringToObject(this.endDate)
            )) {
            throw new Exception("Start date is not before end date");
        }

    }

    private void ValidateDescription() {
        return;
    }

}
