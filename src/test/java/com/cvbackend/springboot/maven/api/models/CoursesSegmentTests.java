package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CoursesSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
        String json = """
            {
                "name": "Advanced Java Programming",
                "instructor": "Jane Doe",
                "completionDate": "2023-12-15T00:00:00.000Z",
                "duration": "6 weeks"
            }
            """;

        CoursesSegment segment = objectMapper.readValue(json, CoursesSegment.class);
        assertThat(segment.getName()).isEqualTo("Advanced Java Programming");
        assertThat(segment.getInstructor()).isEqualTo("Jane Doe");
        assertThat(segment.getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(segment.getDuration()).isEqualTo("6 weeks");

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Advanced Java Programming");
        assertThat(serializedJson).contains("Jane Doe");
        assertThat(serializedJson).contains("2023-12-15T00:00:00.000Z");
        assertThat(serializedJson).contains("6 weeks");
    }
}
