package com.cvbackend.springboot.maven.api.models;

import org.javatuples.Pair;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;

import lombok.Data;

@Data
public class CertificationsSegment {
    private String name;
    private String issuer;
    private String startDate;
    private String endDate;
    private Boolean isNotExpiring;

    private int instanceIndex;
    private Boolean isValid;
    private String validationErrorMsg;

    public Pair<Boolean, String> Validate(int instanceIndex) {

        this.instanceIndex = instanceIndex;
        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateName();
            ValidateIssuer();
            ValidateCertificationDates();

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(isValid, validationErrorMsg);

    }

    private void ValidateName() throws Exception {
        if (this.name == null) {
            throw new Exception("Certification name not supplied for certification instance " + this.instanceIndex);
        }
    }

    private void ValidateIssuer() throws Exception {
        if (this.issuer == null) {
            throw new Exception("Issuer organization not supplied for certification instance " + this.instanceIndex);
        }
    }

    private void ValidateCertificationDates() throws Exception {

        if (this.startDate == null) {
            throw new Exception("Start date not supplied for certification instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsValidDateTime(this.startDate)) {
            throw new Exception("Start date not valid for certification instance " + this.instanceIndex);
        }
        if (this.endDate == null && this.isNotExpiring) {
            return;
        }
        if (this.endDate == null && !this.isNotExpiring) {
            throw new Exception("End date not supplied for certification instance " + this.instanceIndex);
        }
        if (!this.isNotExpiring && !DateTimeUtility.IsValidDateTime(this.endDate)) {
            throw new Exception("End date not valid for certification instance " + this.instanceIndex);
        }
        if (!DateTimeUtility.IsFormerDateEarlierThanLatterDate(
            DateTimeUtility.DateTimeStringToObject(this.startDate), 
            DateTimeUtility.DateTimeStringToObject(this.endDate)
            )) {
            throw new Exception("Start date is not before end date for certification instance " + this.instanceIndex);
        }

    }

}
