package com.cvbackend.springboot.maven.api.models;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.cvbackend.springboot.maven.api.config.TestConfig;


@SpringBootTest
@Import(TestConfig.class)
@AutoConfigureMockMvc
public class GenerateCvRestTests {

    @Autowired
    private MockMvc mockMvc;

    private String json = """
        {
            "personaliaSegment": {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john.doe@example.com",
                "phone": "004799218492",
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

    //@Autowired
    //public GenerateCvRestTests(MockMvc mockMvc) {
    //    this.mockMvc = mockMvc;
    //}

    @Test
    public void testRestResponse() throws Exception {

        this.mockMvc.perform(
                post("/generate-pdf")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
            ).andDo(print()).andExpect(status().isOk());

    }
    
}
