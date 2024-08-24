package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class SkillsSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
        String json = """
            {
                "skills": [
                    {"skill": "Java"},
                    {"skill": "Spring Boot"},
                    {"skill": "Docker"}
                ]
            }
            """;

        SkillsSegment segment = objectMapper.readValue(json, SkillsSegment.class);
        assertThat(segment.getSkillList()).hasSize(3);
        assertThat(segment.getSkillList().get(0).getSkill()).isEqualTo("Java");
        assertThat(segment.getSkillList().get(1).getSkill()).isEqualTo("Spring Boot");
        assertThat(segment.getSkillList().get(2).getSkill()).isEqualTo("Docker");

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Java");
        assertThat(serializedJson).contains("Spring Boot");
        assertThat(serializedJson).contains("Docker");
    }
}
