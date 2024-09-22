package com.cvbackend.springboot.maven.api.models;

import java.util.ArrayList;

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
    private ArrayList<Pair<Boolean, String>> validationResponseList;


    public Pair<Boolean, String> Validate() {

        this.instanceIndex = 0;
        this.isValid = true;
        this.validationErrorMsg = "";

        this.validationResponseList = new ArrayList<>();
        this.validationResponseList.add(personaliaSegment.Validate());
        this.validationResponseList.add(educationSegment.Validate());
        this.validationResponseList.add(experienceSegment.Validate());
        this.validationResponseList.add(skillsSegment.Validate());
        this.validationResponseList.add(certificationsSegment.Validate());
        this.validationResponseList.add(coursesSegment.Validate());

        for (Pair<Boolean, String> validationResponse : validationResponseList) {
            this.isValid = validationResponse.getValue0();
            if (!this.isValid) {
                this.validationErrorMsg = validationResponse.getValue1();
                return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
            }
        }

        return new Pair<Boolean,String>(isValid, validationErrorMsg);

    }
}
