package com.cvbackend.springboot.maven.api.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import com.cvbackend.springboot.maven.api.config.TestConfig;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;
import org.javatuples.Pair;

@JsonTest
@Import(TestConfig.class)
public class CertificationsSegmentTests {

    @Autowired
    private JacksonTester<CertificationsSegment> json;

    private CertificationsSegment certificationsSegment;

    @BeforeEach
    void setUp() throws Exception {
        String jsonContent = """
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
        certificationsSegment = json.parseObject(jsonContent);
    }

    @Test
    void testSerializationAndDeserialization() throws Exception {
        assertThat(certificationsSegment.getCertificationList()).hasSize(2);

        assertThat(certificationsSegment.getCertificationList().get(0).getName()).isEqualTo("Certified Java Developer");
        assertThat(certificationsSegment.getCertificationList().get(0).getIssuer()).isEqualTo("Oracle");
        assertThat(certificationsSegment.getCertificationList().get(0).getStartDate()).isEqualTo("2020-05-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(0).getEndDate()).isEqualTo("2023-05-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(0).getIsNotExpiring()).isFalse();

        assertThat(certificationsSegment.getCertificationList().get(1).getName()).isEqualTo("Certified JavaScript Developer");
        assertThat(certificationsSegment.getCertificationList().get(1).getIssuer()).isEqualTo("Oracle");
        assertThat(certificationsSegment.getCertificationList().get(1).getStartDate()).isEqualTo("2020-02-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(1).getEndDate()).isEqualTo("2010-02-01T00:00:00.000Z");
        assertThat(certificationsSegment.getCertificationList().get(1).getIsNotExpiring()).isTrue();

        String serializedJson = json.write(certificationsSegment).getJson();
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
    public void testValidation() {
        Pair<Boolean, String> validationOutput = certificationsSegment.Validate();
        assertThat(validationOutput.getValue0()).isEqualTo(true);
        assertThat(validationOutput.getValue1()).isNullOrEmpty();
    }
}