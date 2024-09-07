package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.javatuples.Pair;

@JsonTest
public class CertificationsSegmentTests {

    private ObjectMapper objectMapper;
    private CertificationsSegment certificationsSegment;

    @Autowired
    public CertificationsSegmentTests(ObjectMapper objectMapper, CertificationsSegment certificationsSegment) {
        this.objectMapper = objectMapper;
        this.certificationsSegment = certificationsSegment;
    }

    private void setSegment() throws Exception {
        
        String json = """
            {
                "certificationList": [
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

        this.certificationsSegment = this.objectMapper.readValue(json, CertificationsSegment.class);

    }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();
        
        assertThat(this.certificationsSegment.getCertificationList()).hasSize(2);

        assertThat(this.certificationsSegment.getCertificationList().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(0).getIsNotExpiring()).isFalse();

        assertThat(this.certificationsSegment.getCertificationList().get(1).getName()).isEqualTo("Certified JavaScript Developer");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getIssuer()).isEqualTo("Oracle");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getStartDate()).isEqualTo("2020-02-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getEndDate()).isEqualTo("2010-02-01T00:00:00.000Z");
        assertThat(this.certificationsSegment.getCertificationList().get(1).getIsNotExpiring()).isTrue();

        String serializedJson = this.objectMapper.writeValueAsString(this.certificationsSegment);
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

    @Test
    public void testValidation() throws Exception {

        this.setSegment();

        Pair<Boolean, String> validationOutput = this.certificationsSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();

    }

}
