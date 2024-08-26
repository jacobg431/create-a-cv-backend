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
                certificationList: [
                    {
                        "name": "Certified Java Developer",
                        "issuer": "Oracle",
                        "startDate": "2020-05-01T00:00:00.000Z",
                        "endDate": "2023-05-01T00:00:00.000Z",
                        "isNotExpiring": false
                    }, {
                        "name": "Certified JavaScript Developer",
                        "issuer": "Oracle",
                        "startDate": "2020-02-01T00:00:00.000Z",
                        "endDate": "2010-02-01T00:00:00.000Z",
                        "isNotExpiring": true
                    }
                ]
            }
            """;

        CertificationsSegment segment = objectMapper.readValue(json, CertificationsSegment.class);
        assertThat(segment.getCertificationList()).hasSize(2);

        assertThat(segment.getCertificationList().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(segment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(segment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(segment.getCertificationList().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(segment.getCertificationList().get(0).getIsNotExpiring()).isFalse();

        assertThat(segment.getCertificationList().get(0).getName()).isEqualTo("Certified JavaScript Developer");
        assertThat(segment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(segment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-02-01T00:00:00.000Z");
        assertThat(segment.getCertificationList().get(0).getEndDate()).isEqualTo("2010-02-01T00:00:00.000Z");
        assertThat(segment.getCertificationList().get(0).getIsNotExpiring()).isTrue();

        String serializedJson = objectMapper.writeValueAsString(segment);
        assertThat(serializedJson).contains("Certified Java Developer");
        assertThat(serializedJson).contains("Oracle");
        assertThat(serializedJson).contains("2020-05-01T00:00:00.000Z");
        assertThat(serializedJson).contains("2023-05-01T00:00:00.000Z");
        assertThat(serializedJson).contains("false");

        assertThat(serializedJson).contains("Certified JavaScript Developer");
        assertThat(serializedJson).contains("Oracle");
        assertThat(serializedJson).contains("2020-02-01T00:00:00.000Z");
        assertThat(serializedJson).contains("2010-02-01T00:00:00.000Z");
        assertThat(serializedJson).contains("true");
    }
}
