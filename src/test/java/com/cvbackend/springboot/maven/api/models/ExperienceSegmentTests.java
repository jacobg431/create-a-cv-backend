package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ExperienceSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
        String json = """
            {
                "company": "Tech Solutions Inc",
                "position": "Senior Developer",
                "startDate": "2022-01-15T00:00:00.000Z",
                "endDate": "2024-01-15T00:00:00.000Z",
                "isWorking": true,
                "description": "Lead the development of several key projects."
            }
            """;

        ExperienceSegment segment = objectMapper.readValue(json, ExperienceSegment.class);
        assertThat(segment.getCompany()).isEqualTo("Tech Solutions Inc");
        assertThat(segment.getPosition()).isEqualTo("Senior Developer");
        assertThat(segment.getStartDate()).isEqualTo("2022-01-15T00:00:00.000Z");
        assertThat(segment.getEndDate()).isEqualTo("2024-01-15T00:00:00.000Z");
        assertThat(segment.getIsWorking()).isTrue();
        assertThat(segment.getDescription()).isEqualTo("Lead the development of several key projects.");

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Tech Solutions Inc");
        assertThat(serializedJson).contains("Senior Developer");
        assertThat(serializedJson).contains("2022-01-15T00:00:00.000Z");
        assertThat(serializedJson).contains("2024-01-15T00:00:00.000Z");
        assertThat(serializedJson).contains("true");
        assertThat(serializedJson).contains("Lead the development of several key projects.");
    }
}
