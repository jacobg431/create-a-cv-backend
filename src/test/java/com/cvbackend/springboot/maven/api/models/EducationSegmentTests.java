package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class EducationSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
        String json = """
            {
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
            }
            """;

        EducationSegment segment = objectMapper.readValue(json, EducationSegment.class);
        assertThat(segment.getEducationList()).hasSize(2);

        assertThat(segment.getEducationList().get(0).getSchool()).isEqualTo("University of Technology");
        assertThat(segment.getEducationList().get(0).getDegree()).isEqualTo("Master of Science in Computer Science");
        assertThat(segment.getEducationList().get(0).getStartDate()).isEqualTo("2019-09-01T00:00:00.000Z");
        assertThat(segment.getEducationList().get(0).getEndDate()).isEqualTo("2021-06-01T00:00:00.000Z");
        assertThat(segment.getEducationList().get(0).getIsStudying()).isFalse();
        
        assertThat(segment.getEducationList().get(1).getSchool()).isEqualTo("University of Technology");
        assertThat(segment.getEducationList().get(1).getDegree()).isEqualTo("Bachelor of Science in Computer Science");
        assertThat(segment.getEducationList().get(1).getStartDate()).isEqualTo("2016-09-01T00:00:00.000Z");
        assertThat(segment.getEducationList().get(1).getEndDate()).isEqualTo("2019-06-01T00:00:00.000Z");
        assertThat(segment.getEducationList().get(1).getIsStudying()).isFalse();

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("University of Technology");
        assertThat(serializedJson).contains("Master of Science in Computer Science");
        assertThat(serializedJson).contains("2019-09-01T00:00:00.000Z");
        assertThat(serializedJson).contains("2021-06-01T00:00:00.000Z");
        assertThat(serializedJson).contains("false");
        
        assertThat(serializedJson).contains("University of Technology");
        assertThat(serializedJson).contains("Bachelor of Science in Computer Science");
        assertThat(serializedJson).contains("2016-09-01T00:00:00.000Z");
        assertThat(serializedJson).contains("2019-06-01T00:00:00.000Z");
        assertThat(serializedJson).contains("false");
    }
}
