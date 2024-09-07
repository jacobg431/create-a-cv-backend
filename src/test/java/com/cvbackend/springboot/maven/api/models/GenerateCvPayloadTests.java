package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class GenerateCvPayloadTests {

    private ObjectMapper objectMapper;
    private GenerateCvPayload payload;
    private PersonaliaSegment personaliaSegment;
    private EducationSegment educationSegment;
    private ExperienceSegment experienceSegment;
    private SkillsSegment skillsSegment;
    private CertificationsSegment certificationsSegment;
    private CoursesSegment coursesSegment;

    @Autowired
    public GenerateCvPayloadTests(
        ObjectMapper objectMapper,
        GenerateCvPayload payload,
        PersonaliaSegment personaliaSegment,
        EducationSegment educationSegment,
        ExperienceSegment experienceSegment,
        SkillsSegment skillsSegment,
        CertificationsSegment certificationsSegment,
        CoursesSegment coursesSegment
    ) {
        this.objectMapper = objectMapper;
        this.payload = payload;
        this.personaliaSegment = personaliaSegment;
        this.educationSegment = educationSegment;
        this.experienceSegment = experienceSegment;
        this.skillsSegment = skillsSegment;
        this.certificationsSegment = certificationsSegment;
        this.coursesSegment = coursesSegment;
    }

    private void setSegments() throws Exception {

        String json = """
            {
                "personaliaSegment": {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "phone": "1234567890",
                    "dateOfBirth": "1980-01-01T00:00:00.000Z",
                    "gender": "Male",
                    "address": "123 Main St",
                    "address2": "Apt 4",
                    "zipCode": "10001",
                    "city": "New York",
                    "country": "USA",
                    "summary": "Experienced software engineer"
                },
                "educationSegment": {
                    "educationList": [
                        {
                            "school": "University of Technology",
                            "degree": "Master of Science in Computer Science",
                            "startDate": "2019-09-01T00:00:00.000Z",
                            "endDate": "2021-06-01T00:00:00.000Z",
                            "isStudying": false
                        }, {
                            "school": "University of Technology",
                            "degree": "Bachelor of Science in Computer Science",
                            "startDate": "2016-09-01T00:00:00.000Z",
                            "endDate": "2019-06-01T00:00:00.000Z",
                            "isStudying": false
                        }
                    ]
                },
                "experienceSegment": {
                    "experienceList": [
                        {
                            "company": "Tech Solutions Inc",
                            "position": "Senior Developer",
                            "startDate": "2022-01-15T00:00:00.000Z",
                            "endDate": "2024-01-15T00:00:00.000Z",
                            "isWorking": true,
                            "description": "Lead the development of several key projects."
                        }, {
                            "company": "Data Services Ltd",
                            "position": "Junior Developer",
                            "startDate": "2019-01-15T00:00:00.000Z",
                            "endDate": "2022-12-31T12:00:00.000Z",
                            "isWorking": false,
                            "description": "Full stack development in team."
                        }
                    ]
                },
                "skillsSegment": {
                    "skillList": [
                        {"skill": "Java"},
                        {"skill": "Spring Boot"},
                        {"skill": "Docker"}
                    ]
                },
                "certificationsSegment": {
                    "certificationList": [
                        {
                            "name": "Certified Java Developer",
                            "issuer": "Oracle",
                            "startDate": "2020-05-01T00:00:00.000Z",
                            "endDate": "2023-05-01T00:00:00.000Z",
                            "isNotExpiring": false
                        }, {
                            "name": "Certified JavaScript Developer",
                            "issuer": "Oracle",
                            "startDate": "2020-02-01T00:00:00.000Z",
                            "endDate": "2010-02-01T00:00:00.000Z",
                            "isNotExpiring": true
                        }
                    ]
                },
                "coursesSegment": {
                    "courseList": [
                        {                        
                            "name": "Advanced Java Programming",
                            "instructor": "Jane Doe",
                            "completionDate": "2023-12-15T00:00:00.000Z",
                            "duration": "Weeks"
                        }, {
                            "name": "Intermediate Java Programming",
                            "instructor": "Evelyn Monroe",
                            "completionDate": "2023-09-15T00:00:00.000Z",
                            "duration": "Weeks"
                        }
                    ]
                }
            }
            """;

        this.payload = this.objectMapper.readValue(json, GenerateCvPayload.class);
        this.personaliaSegment = this.payload.getPersonaliaSegment();
        this.educationSegment = this.payload.getEducationSegment();
        this.experienceSegment = this.payload.getExperienceSegment();
        this.skillsSegment = this.payload.getSkillsSegment();
        this.certificationsSegment = this.payload.getCertificationsSegment();
        this.coursesSegment = this.payload.getCoursesSegment();

    }

    @Test
    public void testSerializationAndDeserialization() throws Exception {

        this.setSegments();

        assertThat(this.personaliaSegment.getFirstName()).isEqualTo("John");
        assertThat(this.personaliaSegment.getLastName()).isEqualTo("Doe");
        assertThat(this.personaliaSegment.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(this.personaliaSegment.getPhone()).isEqualTo("1234567890");
        assertThat(this.personaliaSegment.getDateOfBirth()).isEqualTo("1980-01-01T00:00:00.000Z");
        assertThat(this.personaliaSegment.getGender()).isEqualTo("Male");
        assertThat(this.personaliaSegment.getAddress()).isEqualTo("123 Main St");
        assertThat(this.personaliaSegment.getAddress2()).isEqualTo("Apt 4");
        assertThat(this.personaliaSegment.getZipCode()).isEqualTo("10001");
        assertThat(this.personaliaSegment.getCity()).isEqualTo("New York");
        assertThat(this.personaliaSegment.getCountry()).isEqualTo("USA");
        assertThat(this.personaliaSegment.getSummary()).isEqualTo("Experienced software engineer");

        assertThat(this.educationSegment.getEducationList()).hasSize(2);
        assertThat(this.educationSegment.getEducationList().get(0).getSchool()).isEqualTo("University of Technology");
        assertThat(this.educationSegment.getEducationList().get(0).getDegree()).isEqualTo("Master of Science in Computer Science");
        assertThat(this.educationSegment.getEducationList().get(0).getStartDate()).isEqualTo("2019-09-01T00:00:00.000Z");
        assertThat(this.educationSegment.getEducationList().get(0).getEndDate()).isEqualTo("2021-06-01T00:00:00.000Z");
        assertThat(this.educationSegment.getEducationList().get(0).getIsStudying()).isFalse();
        assertThat(this.educationSegment.getEducationList().get(1).getSchool()).isEqualTo("University of Technology");
        assertThat(this.educationSegment.getEducationList().get(1).getDegree()).isEqualTo("Bachelor of Science in Computer Science");
        assertThat(this.educationSegment.getEducationList().get(1).getStartDate()).isEqualTo("2016-09-01T00:00:00.000Z");
        assertThat(this.educationSegment.getEducationList().get(1).getEndDate()).isEqualTo("2019-06-01T00:00:00.000Z");
        assertThat(this.educationSegment.getEducationList().get(1).getIsStudying()).isFalse();

        assertThat(this.experienceSegment.getExperienceList()).hasSize(2);
        assertThat(this.experienceSegment.getExperienceList().get(0).getCompany()).isEqualTo("Tech Solutions Inc");
        assertThat(this.experienceSegment.getExperienceList().get(0).getPosition()).isEqualTo("Senior Developer");
        assertThat(this.experienceSegment.getExperienceList().get(0).getStartDate()).isEqualTo("2022-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(0).getEndDate()).isEqualTo("2024-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(0).getIsWorking()).isTrue();
        assertThat(this.experienceSegment.getExperienceList().get(0).getDescription()).isEqualTo("Lead the development of several key projects.");
        assertThat(this.experienceSegment.getExperienceList().get(1).getCompany()).isEqualTo("Data Services Ltd");
        assertThat(this.experienceSegment.getExperienceList().get(1).getPosition()).isEqualTo("Junior Developer");
        assertThat(this.experienceSegment.getExperienceList().get(1).getStartDate()).isEqualTo("2019-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(1).getEndDate()).isEqualTo("2022-12-31T12:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(1).getIsWorking()).isFalse();
        assertThat(this.experienceSegment.getExperienceList().get(1).getDescription()).isEqualTo("Full stack development in team.");

        assertThat(this.skillsSegment.getSkillList()).hasSize(3);
        assertThat(this.skillsSegment.getSkillList().get(0).getSkill()).isEqualTo("Java");
        assertThat(this.skillsSegment.getSkillList().get(1).getSkill()).isEqualTo("Spring Boot");
        assertThat(this.skillsSegment.getSkillList().get(2).getSkill()).isEqualTo("Docker");

        assertThat(this.certificationsSegment.getCertificationList()).hasSize(2);
        assertThat(this.certificationsSegment.getCertificationList().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getIsNotExpiring()).isFalse();
        assertThat(this.certificationsSegment.getCertificationList().get(1).getName()).isEqualTo("Certified JavaScript Developer");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getIssuer()).isEqualTo("Oracle");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getStartDate()).isEqualTo("2020-02-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getEndDate()).isEqualTo("2010-02-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getIsNotExpiring()).isTrue();

        assertThat(this.coursesSegment.getCourseList()).hasSize(2);
        assertThat(this.coursesSegment.getCourseList().get(0).getName()).isEqualTo("Advanced Java Programming");
        assertThat(this.coursesSegment.getCourseList().get(0).getInstructor()).isEqualTo("Jane Doe");
        assertThat(this.coursesSegment.getCourseList().get(0).getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(this.coursesSegment.getCourseList().get(0).getDuration()).isEqualTo("Weeks");
        assertThat(this.coursesSegment.getCourseList().get(1).getName()).isEqualTo("Intermediate Java Programming");
        assertThat(this.coursesSegment.getCourseList().get(1).getInstructor()).isEqualTo("Evelyn Monroe");
        assertThat(this.coursesSegment.getCourseList().get(1).getCompletionDate()).isEqualTo("2023-09-15T00:00:00.000Z");
        assertThat(this.coursesSegment.getCourseList().get(1).getDuration()).isEqualTo("Weeks");

        String serializedJson = this.objectMapper.writeValueAsString(this.payload);
        assertThat(serializedJson).contains("\"firstName\":\"John\"");
        assertThat(serializedJson).contains("\"lastName\":\"Doe\"");
        assertThat(serializedJson).contains("University of Technology");
        assertThat(serializedJson).contains("Tech Solutions Inc");
        assertThat(serializedJson).contains("Java");
        assertThat(serializedJson).contains("Certified Java Developer");
        assertThat(serializedJson).contains("Advanced Java Programming");

    }

    @Test
    public void testValidation() throws Exception {
        this.setSegments();
    }

}
