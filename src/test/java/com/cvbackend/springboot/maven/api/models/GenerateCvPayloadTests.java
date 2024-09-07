package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class GenerateCvPayloadTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
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

        GenerateCvPayload payload = objectMapper.readValue(json, GenerateCvPayload.class);

        assertThat(payload.getPersonaliaSegment().getFirstName()).isEqualTo("John");
        assertThat(payload.getPersonaliaSegment().getLastName()).isEqualTo("Doe");
        assertThat(payload.getPersonaliaSegment().getEmail()).isEqualTo("john.doe@example.com");
        assertThat(payload.getPersonaliaSegment().getPhone()).isEqualTo("1234567890");
        assertThat(payload.getPersonaliaSegment().getDateOfBirth()).isEqualTo("1980-01-01T00:00:00.000Z");
        assertThat(payload.getPersonaliaSegment().getGender()).isEqualTo("Male");
        assertThat(payload.getPersonaliaSegment().getAddress()).isEqualTo("123 Main St");
        assertThat(payload.getPersonaliaSegment().getAddress2()).isEqualTo("Apt 4");
        assertThat(payload.getPersonaliaSegment().getZipCode()).isEqualTo("10001");
        assertThat(payload.getPersonaliaSegment().getCity()).isEqualTo("New York");
        assertThat(payload.getPersonaliaSegment().getCountry()).isEqualTo("USA");
        assertThat(payload.getPersonaliaSegment().getSummary()).isEqualTo("Experienced software engineer");

        EducationSegment payloadEducationSegment = payload.getEducationSegment();
        assertThat(payloadEducationSegment.getEducationList()).hasSize(2);
        assertThat(payloadEducationSegment.getEducationList().get(0).getSchool()).isEqualTo("University of Technology");
        assertThat(payloadEducationSegment.getEducationList().get(0).getDegree()).isEqualTo("Master of Science in Computer Science");
        assertThat(payloadEducationSegment.getEducationList().get(0).getStartDate()).isEqualTo("2019-09-01T00:00:00.000Z");
        assertThat(payloadEducationSegment.getEducationList().get(0).getEndDate()).isEqualTo("2021-06-01T00:00:00.000Z");
        assertThat(payloadEducationSegment.getEducationList().get(0).getIsStudying()).isFalse();
        assertThat(payloadEducationSegment.getEducationList().get(1).getSchool()).isEqualTo("University of Technology");
        assertThat(payloadEducationSegment.getEducationList().get(1).getDegree()).isEqualTo("Bachelor of Science in Computer Science");
        assertThat(payloadEducationSegment.getEducationList().get(1).getStartDate()).isEqualTo("2016-09-01T00:00:00.000Z");
        assertThat(payloadEducationSegment.getEducationList().get(1).getEndDate()).isEqualTo("2019-06-01T00:00:00.000Z");
        assertThat(payloadEducationSegment.getEducationList().get(1).getIsStudying()).isFalse();

        ExperienceSegment payloExperienceSegment = payload.getExperienceSegment();
        assertThat(payloExperienceSegment.getExperienceList()).hasSize(2);
        assertThat(payloExperienceSegment.getExperienceList().get(0).getCompany()).isEqualTo("Tech Solutions Inc");
        assertThat(payloExperienceSegment.getExperienceList().get(0).getPosition()).isEqualTo("Senior Developer");
        assertThat(payloExperienceSegment.getExperienceList().get(0).getStartDate()).isEqualTo("2022-01-15T00:00:00.000Z");
        assertThat(payloExperienceSegment.getExperienceList().get(0).getEndDate()).isEqualTo("2024-01-15T00:00:00.000Z");
        assertThat(payloExperienceSegment.getExperienceList().get(0).getIsWorking()).isTrue();
        assertThat(payloExperienceSegment.getExperienceList().get(0).getDescription()).isEqualTo("Lead the development of several key projects.");
        assertThat(payloExperienceSegment.getExperienceList().get(1).getCompany()).isEqualTo("Data Services Ltd");
        assertThat(payloExperienceSegment.getExperienceList().get(1).getPosition()).isEqualTo("Junior Developer");
        assertThat(payloExperienceSegment.getExperienceList().get(1).getStartDate()).isEqualTo("2019-01-15T00:00:00.000Z");
        assertThat(payloExperienceSegment.getExperienceList().get(1).getEndDate()).isEqualTo("2022-12-31T12:00:00.000Z");
        assertThat(payloExperienceSegment.getExperienceList().get(1).getIsWorking()).isFalse();
        assertThat(payloExperienceSegment.getExperienceList().get(1).getDescription()).isEqualTo("Full stack development in team.");

        SkillsSegment payloadSkillsSegment = payload.getSkillsSegment();
        assertThat(payloadSkillsSegment.getSkillList()).hasSize(3);
        assertThat(payloadSkillsSegment.getSkillList().get(0).getSkill()).isEqualTo("Java");
        assertThat(payloadSkillsSegment.getSkillList().get(1).getSkill()).isEqualTo("Spring Boot");
        assertThat(payloadSkillsSegment.getSkillList().get(2).getSkill()).isEqualTo("Docker");

        CertificationsSegment certificationsSegment = payload.getCertificationsSegment();
        assertThat(certificationsSegment.getCertificationList()).hasSize(2);
        assertThat(certificationsSegment.getCertificationList().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(certificationsSegment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(certificationsSegment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(0).getIsNotExpiring()).isFalse();
        assertThat(certificationsSegment.getCertificationList().get(1).getName()).isEqualTo("Certified JavaScript Developer");
        assertThat(certificationsSegment.getCertificationList().get(1).getIssuer()).isEqualTo("Oracle");
        assertThat(certificationsSegment.getCertificationList().get(1).getStartDate()).isEqualTo("2020-02-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(1).getEndDate()).isEqualTo("2010-02-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(1).getIsNotExpiring()).isTrue();

        CoursesSegment coursesSegment = payload.getCoursesSegment();
        assertThat(coursesSegment.getCourseList()).hasSize(2);
        assertThat(coursesSegment.getCourseList().get(0).getName()).isEqualTo("Advanced Java Programming");
        assertThat(coursesSegment.getCourseList().get(0).getInstructor()).isEqualTo("Jane Doe");
        assertThat(coursesSegment.getCourseList().get(0).getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(coursesSegment.getCourseList().get(0).getDuration()).isEqualTo("Weeks");
        assertThat(coursesSegment.getCourseList().get(1).getName()).isEqualTo("Intermediate Java Programming");
        assertThat(coursesSegment.getCourseList().get(1).getInstructor()).isEqualTo("Evelyn Monroe");
        assertThat(coursesSegment.getCourseList().get(1).getCompletionDate()).isEqualTo("2023-09-15T00:00:00.000Z");
        assertThat(coursesSegment.getCourseList().get(1).getDuration()).isEqualTo("Weeks");

        String serializedJson = objectMapper.writeValueAsString(payload);
        assertThat(serializedJson).contains("\"firstName\":\"John\"");
        assertThat(serializedJson).contains("\"lastName\":\"Doe\"");
        assertThat(serializedJson).contains("University of Technology");
        assertThat(serializedJson).contains("Tech Solutions Inc");
        assertThat(serializedJson).contains("Java");
        assertThat(serializedJson).contains("Certified Java Developer");
        assertThat(serializedJson).contains("Advanced Java Programming");
    }
}
