package com.cvbackend.springboot.maven.api.models;

import java.util.List;

import org.javatuples.Pair;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;
import lombok.Data;

@Data
public class EducationSegment {

    @Data
    public static class Education {

        private String school;
        private String degree;
        private String startDate;
        private String endDate;
        private Boolean isStudying;
        private int instanceIndex;

        public void Validate(int instanceIndex) throws Exception {

            this.instanceIndex = instanceIndex;

            ValidateSchool();
            ValidateDegree();
            ValidateEducationDates();

        }

        private void ValidateSchool() throws Exception {
            if (this.school == null) {
                throw new Exception("School name not supplied for education instance " + this.instanceIndex);
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
                throw new Exception("Start date is not before end date for education instance " + this.instanceIndex);
            }
    
        }

    }

    private List<Education> educationList;
    private Boolean isValid;
    private String validationErrorMsg;
    private int instanceIndex;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";
        this.instanceIndex = 0;

        try {

            for (Education education : this.educationList) {
                education.Validate(this.instanceIndex);
                this.instanceIndex++;
            }

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

}
