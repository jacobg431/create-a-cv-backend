package com.cvbackend.springboot.maven.api.models;

import java.util.List;
import lombok.Data;

@Data
public class GenerateCvPayload {
    private PersonaliaSegment personaliaSegment;
    private List<EducationSegment> educationSegment;
    private List<ExperienceSegment> experienceSegment;
    private SkillsSegment skillsSegment;
    private List<CertificationsSegment> certificationsSegment;
    private List<CoursesSegment> coursesSegment;
}
