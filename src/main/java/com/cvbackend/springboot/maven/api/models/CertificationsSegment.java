package com.cvbackend.springboot.maven.api.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class CertificationsSegment extends AbstractSegment {

    @Data
    public static class Certification {

        private String name;
        private String issuer;
        private String startDate;
        private String endDate;
        private Boolean isNotExpiring;
        private int instanceIndex;

        public void Validate(int instanceIndex) throws Exception {

            this.instanceIndex = instanceIndex;

            ValidateName();
            ValidateIssuer();
            ValidateCertificationDates();

        }

        private void ValidateName() throws Exception {
            if (this.name == null || this.name.isBlank()) {
                throw new Exception("Certification name not supplied for certification instance " + this.instanceIndex);
            }
        }
    
        private void ValidateIssuer() throws Exception {
            if (this.issuer == null || this.issuer.isBlank()) {
                throw new Exception("Issuer organization not supplied for certification instance " + this.instanceIndex);
            }
        }
    
        private void ValidateCertificationDates() throws Exception {
    
            if (this.startDate == null || this.startDate.isBlank()) {
                throw new Exception("Start date not supplied for certification instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(this.startDate)) {
                throw new Exception("Start date not valid for certification instance " + this.instanceIndex);
            }
            if (this.isNotExpiring) {
                return;
            }
            if (this.endDate == null || this.endDate.isBlank()) {
                throw new Exception("End date not supplied for certification instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(this.endDate)) {
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

    private List<Certification> certificationList; 
    private int instanceIndex;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";
        this.instanceIndex = 0;

        try {

            for (Certification certification : this.certificationList) {
                certification.Validate(this.instanceIndex);
                this.instanceIndex++;
            }

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

}
