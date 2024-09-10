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
public class ExperienceSegmentTests {

    private ObjectMapper objectMapper;
    private ExperienceSegment experienceSegment;

    @Autowired
    public ExperienceSegmentTests(ObjectMapper objectMapper, ExperienceSegment experienceSegment) {
        this.objectMapper = objectMapper;
        this.experienceSegment = experienceSegment;
    }

    private void setSegment() throws Exception {

        String json = """
            {
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
            }
            """;

        this.experienceSegment = this.objectMapper.readValue(json, ExperienceSegment.class);

    }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();

        assertThat(this.experienceSegment.getExperienceList()).hasSize(2);

        assertThat(this.experienceSegment.getExperienceList().get(0).getCompany()).isEqualTo("Tech Solutions Inc");
        assertThat(this.experienceSegment.getExperienceList().get(0).getPosition()).isEqualTo("Senior Developer");
        assertThat(this.experienceSegment.getExperienceList().get(0).getStartDate()).isEqualTo("2022-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(0).getEndDate()).isEqualTo("2024-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(0).getIsWorking()).isTrue();
        assertThat(this.experienceSegment.getExperienceList().get(0).getDescription()).isEqualTo("Lead the development of several key projects.");

        assertThat(this.experienceSegment.getExperienceList().get(1).getCompany()).isEqualTo("Data Services Ltd");
        assertThat(this.experienceSegment.getExperienceList().get(1).getPosition()).isEqualTo("Junior Developer");
        assertThat(this.experienceSegment.getExperienceList().get(1).getStartDate()).isEqualTo("2019-01-15T00:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(1).getEndDate()).isEqualTo("2022-12-31T12:00:00.000Z");
        assertThat(this.experienceSegment.getExperienceList().get(1).getIsWorking()).isFalse();
        assertThat(this.experienceSegment.getExperienceList().get(1).getDescription()).isEqualTo("Full stack development in team.");

        String serializedJson = this.objectMapper.writeValueAsString(this.experienceSegment);
        assertThat(serializedJson).contains("Tech Solutions Inc");
        assertThat(serializedJson).contains("Senior Developer");
        assertThat(serializedJson).contains("2022-01-15T00:00:00.000Z");
        assertThat(serializedJson).contains("2024-01-15T00:00:00.000Z");
        assertThat(serializedJson).contains("true");
        assertThat(serializedJson).contains("Lead the development of several key projects.");

        assertThat(serializedJson).contains("Data Services Ltd");
        assertThat(serializedJson).contains("Junior Developer");
        assertThat(serializedJson).contains("2019-01-15T00:00:00.000Z");
        assertThat(serializedJson).contains("2022-12-31T12:00:00.000Z");
        assertThat(serializedJson).contains("false");
        assertThat(serializedJson).contains("Full stack development in team.");
    }

    @Test
    public void testValidation() throws Exception {

        this.setSegment();

        Pair<Boolean, String> validationOutput = this.experienceSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();

    }

}
