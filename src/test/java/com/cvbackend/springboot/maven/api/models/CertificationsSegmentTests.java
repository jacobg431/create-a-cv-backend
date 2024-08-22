package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CertificationsSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
        String json = """
            {
                "name": "Certified Java Developer",
                "issuer": "Oracle",
                "startDate": "2020-05-01T00:00:00.000Z",
                "endDate": "2023-05-01T00:00:00.000Z",
                "isNotExpiring": false
            }
            """;

        CertificationsSegment segment = objectMapper.readValue(json, CertificationsSegment.class);
        assertThat(segment.getName()).isEqualTo("Certified Java Developer");
        assertThat(segment.getIssuer()).isEqualTo("Oracle");
        assertThat(segment.getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(segment.getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(segment.getIsNotExpiring()).isFalse();

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Certified Java Developer");
        assertThat(serializedJson).contains("Oracle");
        assertThat(serializedJson).contains("2020-05-01T00:00:00.000Z");
        assertThat(serializedJson).contains("2023-05-01T00:00:00.000Z");
        assertThat(serializedJson).contains("false");
    }
}
