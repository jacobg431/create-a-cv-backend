package com.cvbackend.springboot.maven.api.models;

import java.util.List;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import com.cvbackend.springboot.maven.api.utils.DateTimeUtility;
import lombok.Data;

@Data
@Component
public class ExperienceSegment {

    @Data
    public static class Experience  {

        private String company;
        private String position;
        private String startDate;
        private String endDate;
        private Boolean isWorking;
        private String description;
        private int instanceIndex;

        public void Validate(int instanceIndex) throws Exception {

            this.instanceIndex = instanceIndex;

            ValidateCompany();
            ValidatePosition();
            ValidateExperienceDates();
            ValidateDescription();

        }

        private void ValidateCompany() throws Exception {
            if (this.company == null || this.company.isBlank()) {
                throw new Exception("Company name not supplied for experience instance " + this.instanceIndex);
            }
        }
    
        private void ValidatePosition() throws Exception {
            if (this.position == null || this.position.isBlank()) {
                throw new Exception("Position not supplied for experience instance " + this.instanceIndex);
            }
        }
    
        private void ValidateExperienceDates() throws Exception {
    
            if (this.startDate == null || this.startDate.isBlank()) {
                throw new Exception("Start date not supplied for experience instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(startDate)) {
                throw new Exception("Start date not valid for experience instance " + this.instanceIndex);
            }
            if (this.isWorking) {
                return;
            }
            if (this.endDate == null || this.endDate.isBlank()) {
                throw new Exception("End date not supplied for experience instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsValidDateTime(this.endDate)) {
                throw new Exception("End date not valid for experience instance " + this.instanceIndex);
            }
            if (!DateTimeUtility.IsFormerDateEarlierThanLatterDate(
                DateTimeUtility.DateTimeStringToObject(this.startDate), 
                DateTimeUtility.DateTimeStringToObject(this.endDate)
                )) {
                throw new Exception("Start date is not before end date for experience instance " + this.instanceIndex);
            }
    
        }
    
        private void ValidateDescription() {
            return;
        }

    }

    private List<Experience> experienceList;
    private Boolean isValid;
    private String validationErrorMsg;
    private int instanceIndex;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";
        this.instanceIndex = 0;

        try {

            for (Experience experience : this.experienceList) {
                experience.Validate(this.instanceIndex);
                this.instanceIndex++;
            }

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

}
