package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.javatuples.Pair;

@JsonTest
public class SkillsSegmentTests {

    private ObjectMapper objectMapper;
    private SkillsSegment skillsSegment;

    @Autowired
    public SkillsSegmentTests(ObjectMapper objectMapper, SkillsSegment skillsSegment) {
        this.objectMapper = objectMapper;
        this.skillsSegment = skillsSegment;
    }

    private void setSegment() throws Exception {
        
        String json = """
            {
                "skillList": [
                    {"skill": "Java"},
                    {"skill": "Spring Boot"},
                    {"skill": "Docker"}
                ]
            }
            """;

        this.skillsSegment = this.objectMapper.readValue(json, SkillsSegment.class);

    }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();

        assertThat(this.skillsSegment.getSkillList()).hasSize(3);
        assertThat(this.skillsSegment.getSkillList().get(0).getSkill()).isEqualTo("Java");
        assertThat(this.skillsSegment.getSkillList().get(1).getSkill()).isEqualTo("Spring Boot");
        assertThat(this.skillsSegment.getSkillList().get(2).getSkill()).isEqualTo("Docker");

        String serializedJson = this.objectMapper.writeValueAsString(this.skillsSegment);
        assertThat(serializedJson).contains("Java");
        assertThat(serializedJson).contains("Spring Boot");
        assertThat(serializedJson).contains("Docker");
        
    }

    @Test
    public void testValidation() throws Exception {

        this.setSegment();

        Pair<Boolean, String> validationOutput = this.skillsSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();

    }

}
