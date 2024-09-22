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
    private Pair<Boolean, String> validationResponse;
    private ArrayList<AbstractSegment> segmentList;


    public Pair<Boolean, String> Validate() {

        this.instanceIndex = 0;
        this.isValid = true;
        this.validationErrorMsg = "";

        this.segmentList = new ArrayList<>();
        this.segmentList.add(personaliaSegment);
        this.segmentList.add(educationSegment);
        this.segmentList.add(experienceSegment);
        this.segmentList.add(skillsSegment);
        this.segmentList.add(certificationsSegment);
        this.segmentList.add(coursesSegment);

        for (AbstractSegment iSegment : segmentList) {
            this.validationResponse = iSegment.Validate();
            this.isValid = validationResponse.getValue0();
            if (!this.isValid) {
                this.validationErrorMsg = validationResponse.getValue1();
                return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);
            }
        }

        return new Pair<Boolean,String>(isValid, validationErrorMsg);

    }
}
