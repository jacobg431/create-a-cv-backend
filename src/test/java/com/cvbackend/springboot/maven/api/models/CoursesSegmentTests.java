package com.cvbackend.springboot.maven.api.models;

import com.cvbackend.springboot.maven.api.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

import org.javatuples.Pair;

@JsonTest
@Import(TestConfig.class)
public class CoursesSegmentTests {

    private ObjectMapper objectMapper;
    private CoursesSegment coursesSegment;

    @Autowired
    public CoursesSegmentTests(ObjectMapper objectMapper, CoursesSegment coursesSegment) {
        this.objectMapper = objectMapper;
        this.coursesSegment = coursesSegment;
    }

    private void setSegment() throws Exception {
        
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

        this.coursesSegment = this.objectMapper.readValue(json, CoursesSegment.class);

    }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();

        assertThat(this.coursesSegment.getCourseList()).hasSize(2);

        assertThat(this.coursesSegment.getCourseList().get(0).getName()).isEqualTo("Advanced Java Programming");
        assertThat(this.coursesSegment.getCourseList().get(0).getInstructor()).isEqualTo("Jane Doe");
        assertThat(this.coursesSegment.getCourseList().get(0).getCompletionDate()).isEqualTo("2023-12-15T00:00:00.000Z");
        assertThat(this.coursesSegment.getCourseList().get(0).getDuration()).isEqualTo("Weeks");

        assertThat(this.coursesSegment.getCourseList().get(1).getName()).isEqualTo("Intermediate Java Programming");
        assertThat(this.coursesSegment.getCourseList().get(1).getInstructor()).isEqualTo("Evelyn Monroe");
        assertThat(this.coursesSegment.getCourseList().get(1).getCompletionDate()).isEqualTo("2023-09-15T00:00:00.000Z");
        assertThat(this.coursesSegment.getCourseList().get(1).getDuration()).isEqualTo("Weeks");

        String serializedJson = this.objectMapper.writeValueAsString(this.coursesSegment);
        assertThat(serializedJson).contains("Advanced Java Programming");
        assertThat(serializedJson).contains("Jane Doe");
        assertThat(serializedJson).contains("2023-12-15T00:00:00.000Z");
        assertThat(serializedJson).contains("Weeks");
        
        assertThat(serializedJson).contains("Intermediate Java Programming");
        assertThat(serializedJson).contains("Evelyn Monroe");
        assertThat(serializedJson).contains("2023-09-15T00:00:00.000Z");
        assertThat(serializedJson).contains("Weeks");
    }

    @Test
    public void testValidation() throws Exception {

        this.setSegment();

        Pair<Boolean, String> validationOutput = this.coursesSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();

    }

}
