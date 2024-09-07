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
            """;

        CoursesSegment segment = objectMapper.readValue(json, CoursesSegment.class);
        assertThat(segment.getCourseList()).hasSize(2);

        assertThat(segment.getCourseList().get(0).getName()).isEqualTo("Advanced Java Programming");
        assertThat(segment.getCourseList().get(0).getInstructor()).isEqualTo("Jane Doe");
        assertThat(segment.getCourseList().get(0).getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(segment.getCourseList().get(0).getDuration()).isEqualTo("Weeks");

        assertThat(segment.getCourseList().get(1).getName()).isEqualTo("Intermediate Java Programming");
        assertThat(segment.getCourseList().get(1).getInstructor()).isEqualTo("Evelyn Monroe");
        assertThat(segment.getCourseList().get(1).getCompletionDate()).isEqualTo("2023-09-15T00:00:00.000Z");
        assertThat(segment.getCourseList().get(1).getDuration()).isEqualTo("Weeks");

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Advanced Java Programming");
        assertThat(serializedJson).contains("Jane Doe");
        assertThat(serializedJson).contains("2023-12-15T00:00:00.000Z");
        assertThat(serializedJson).contains("Weeks");
        
        assertThat(serializedJson).contains("Intermediate Java Programming");
        assertThat(serializedJson).contains("Evelyn Monroe");
        assertThat(serializedJson).contains("2023-09-15T00:00:00.000Z");
        assertThat(serializedJson).contains("Weeks");
    }
}
