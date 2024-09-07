package com.cvbackend.springboot.maven.api.models;

import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GenerateCvPayload {

    private PersonaliaSegment personaliaSegment;
    private EducationSegment educationSegment;
    private ExperienceSegment experienceSegment;
    private SkillsSegment skillsSegment;
    private CertificationsSegment certificationsSegment;
    private CoursesSegment coursesSegment;

    private int instanceIndex;
    private Boolean isValid;
    private String validationErrorMsg;
    private Pair<Boolean, String> validationResponse;


    public Pair<Boolean, String> Validate() {

        this.instanceIndex = 0;
        this.isValid = true;
        this.validationErrorMsg = "";

        this.validationResponse = personaliaSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }
        
        this.validationResponse = skillsSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }
        
        this.validationResponse = educationSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }
        
        this.validationResponse = experienceSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }
        
        this.validationResponse = certificationsSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }
        
        this.validationResponse = coursesSegment.Validate();
        this.isValid = validationResponse.getValue0();
        if (!this.isValid) {
            this.validationErrorMsg = validationResponse.getValue1();
            return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
        }

        return new Pair<Boolean,String>(isValid, validationErrorMsg);

    }
}
