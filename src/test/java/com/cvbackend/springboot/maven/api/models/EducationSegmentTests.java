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
public class EducationSegmentTests {

    private ObjectMapper objectMapper;
    private EducationSegment educationSegment;

    @Autowired
    public EducationSegmentTests(ObjectMapper objectMapper, EducationSegment educationSegment) {
        this.objectMapper = objectMapper;
        this.educationSegment = educationSegment;
    }

    private void setSegment() throws Exception {

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

        this.educationSegment = this.objectMapper.readValue(json, EducationSegment.class);

    }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();

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

        String serializedJson = this.objectMapper.writeValueAsString(this.educationSegment);
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

    @Test
    public void testValidation() throws Exception {

        this.setSegment();

        Pair<Boolean, String> validationOutput = this.educationSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();

    }
    
}
