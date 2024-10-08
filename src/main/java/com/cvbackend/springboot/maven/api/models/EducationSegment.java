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
public class EducationSegment extends AbstractSegment {

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
            if (this.school == null || this.school.isBlank()) {
                throw new Exception("School name not supplied for education instance " + this.instanceIndex);
            }
        }
    
        private void ValidateDegree() {
            return;
        }
    
        private void ValidateEducationDates() throws Exception {
    
            if (this.startDate == null || this.startDate.isBlank()) {
                throw new Exception("Start date not supplied for education instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(this.startDate)) {
                throw new Exception("Start date not valid for education instance " + this.instanceIndex);
            }
            if (this.isStudying) {
                return;
            }
            if (this.endDate == null || this.endDate.isBlank()) {
                throw new Exception("End date not supplied for education instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(this.endDate)) {
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
