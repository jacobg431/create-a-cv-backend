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
                "educationSegment": [
                    {
                        "school": "University of Technology",
                        "degree": "Master of Science in Computer Science",
                        "startDate": "2019-09-01T00:00:00.000Z",
                        "endDate": "2021-06-01T00:00:00.000Z",
                        "isStudying": false
                    }
                ],
                "experienceSegment": [
                    {
                        "company": "Tech Solutions Inc",
                        "position": "Senior Developer",
                        "startDate": "2022-01-15T00:00:00.000Z",
                        "endDate": "2024-01-15T00:00:00.000Z",
                        "isWorking": true,
                        "description": "Lead the development of several key projects."
                    }
                ],
                "skillsSegment": {
                    "skills": [
                        {"skill": "Java"},
                        {"skill": "Spring Boot"}
                    ],
                    "input": "Java development"
                },
                "certificationsSegment": [
                    {
                        "name": "Certified Java Developer",
                        "issuer": "Oracle",
                        "startDate": "2020-05-01T00:00:00.000Z",
                        "endDate": "2023-05-01T00:00:00.000Z",
                        "isNotExpiring": false
                    }
                ],
                "coursesSegment": [
                    {
                        "name": "Advanced Java Programming",
                        "instructor": "Jane Doe",
                        "completionDate": "2023-12-15T00:00:00.000Z",
                        "duration": "6 weeks"
                    }
                ]
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

        assertThat(payload.getEducationSegment()).hasSize(1);
        assertThat(payload.getEducationSegment().get(0).getSchool()).isEqualTo("University of Technology");
        assertThat(payload.getEducationSegment().get(0).getDegree()).isEqualTo("Master of Science in Computer Science");
        assertThat(payload.getEducationSegment().get(0).getStartDate()).isEqualTo("2019-09-01T00:00:00.000Z");
        assertThat(payload.getEducationSegment().get(0).getEndDate()).isEqualTo("2021-06-01T00:00:00.000Z");
        assertThat(payload.getEducationSegment().get(0).getIsStudying()).isFalse();

        assertThat(payload.getExperienceSegment()).hasSize(1);
        assertThat(payload.getExperienceSegment().get(0).getCompany()).isEqualTo("Tech Solutions Inc");
        assertThat(payload.getExperienceSegment().get(0).getPosition()).isEqualTo("Senior Developer");
        assertThat(payload.getExperienceSegment().get(0).getStartDate()).isEqualTo("2022-01-15T00:00:00.000Z");
        assertThat(payload.getExperienceSegment().get(0).getEndDate()).isEqualTo("2024-01-15T00:00:00.000Z");
        assertThat(payload.getExperienceSegment().get(0).getIsWorking()).isTrue();
        assertThat(payload.getExperienceSegment().get(0).getDescription()).isEqualTo("Lead the development of several key projects.");

        assertThat(payload.getSkillsSegment().getSkills()).hasSize(2);
        assertThat(payload.getSkillsSegment().getSkills().get(0).getSkill()).isEqualTo("Java");
        assertThat(payload.getSkillsSegment().getSkills().get(1).getSkill()).isEqualTo("Spring Boot");
        assertThat(payload.getSkillsSegment().getInput()).isEqualTo("Java development");

        assertThat(payload.getCertificationsSegment()).hasSize(1);
        assertThat(payload.getCertificationsSegment().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(payload.getCertificationsSegment().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(payload.getCertificationsSegment().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(payload.getCertificationsSegment().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(payload.getCertificationsSegment().get(0).getIsNotExpiring()).isFalse();

        assertThat(payload.getCoursesSegment()).hasSize(1);
        assertThat(payload.getCoursesSegment().get(0).getName()).isEqualTo("Advanced Java Programming");
        assertThat(payload.getCoursesSegment().get(0).getInstructor()).isEqualTo("Jane Doe");
        assertThat(payload.getCoursesSegment().get(0).getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(payload.getCoursesSegment().get(0).getDuration()).isEqualTo("6 weeks");

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
