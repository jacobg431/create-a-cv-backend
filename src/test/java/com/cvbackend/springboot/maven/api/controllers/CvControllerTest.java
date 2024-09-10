package com.cvbackend.springboot.maven.api.controllers;

import com.cvbackend.springboot.maven.api.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {CvController.class, TestConfig.class})
@AutoConfigureMockMvc(addFilters = false)
@Import(TestConfig.class)
public class CvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGeneratePdf() throws Exception {
        String jsonPayload = """
{
    "personaliaSegment": {
        "firstName": "John",
        "lastName": "Doe",
        "email": "johndoe@example.com",
        "phone": "12345678",
        "dateOfBirth": "2022-10-31T23:00:00.000Z",
        "gender": "Male",
        "address": "Streetname 123",
        "address2": "",
        "zipCode": "1234",
        "city": "New york",
        "country": "United States",
        "summary": "I am a cool guy.",
        "profilePicture": null
    },
    "educationSegment": {
        "educationList": [
            {
                "school": "School of Technology",
                "degree": "Pro programmer",
                "startDate": "2024-02-29T23:00:00.000Z",
                "endDate": "2024-08-31T22:00:00.000Z",
                "isStudying": false
            }
        ]
    },
    "experienceSegment": {
        "experienceList": [
            {
                "company": "Best Company",
                "position": "Pro Developer",
                "startDate": "2024-08-31T22:00:00.000Z",
                "endDate": "2024-09-10T16:19:37.257Z",
                "isWorking": true,
                "description": "Worked on coding and stuff"
            }
        ]
    },
    "skillsSegment": {
        "input": "",
        "skillList": [
            {
                "skill": "programming"
            },
            {
                "skill": "coding"
            }
        ]
    },
    "certificationsSegment": {
        "certificationList": [
            {
                "name": "Certified Pro",
                "issuer": "Myself",
                "startDate": "2024-08-31T22:00:00.000Z",
                "endDate": "2024-09-01T22:00:00.000Z",
                "isNotExpiring": true
            }
        ]
    },
    "coursesSegment": {
        "courseList": [
            {
                "name": "How to program",
                "instructor": "YouTube",
                "completionDate": "2024-09-10T16:19:37.257Z",
                "duration": "Hours"
            }
        ]
    }
}
""";

        System.out.println("Request Payload: " + jsonPayload);

        MvcResult result = mockMvc.perform(post("/generate-pdf")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_OCTET_STREAM)
            .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM))
            .andExpect(header().string("Content-Disposition", "attachment; filename=cv.pdf"))
            .andReturn();

        if (result.getResponse().getStatus() != 200) {
            System.out.println("Response Status: " + result.getResponse().getStatus());
            System.out.println("Response Content: " + result.getResponse().getContentAsString());
        }
    }
}