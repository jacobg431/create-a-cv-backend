package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PersonaliaSegmentTests {

    private ObjectMapper objectMapper;
    private PersonaliaSegment personaliaSegment;

    @Autowired
    public PersonaliaSegmentTests(ObjectMapper objectMapper, PersonaliaSegment personaliaSegment) {
        this.objectMapper = objectMapper;
        this.personaliaSegment = personaliaSegment;
    }

    private void setSegment() throws Exception {

        String json = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john.doe@example.com",
                "phone": "1234567890",
                "dateOfBirth": "1980-01-01T00:00:00.000Z",
                "gender": "Male",
                "address": "123 Main St",
                "address2": "Apt 4",
                "zipCode": "10001",
                "city": "New York",
                "country": "USA",
                "summary": "Experienced software engineer"
            }
            """;

            this.personaliaSegment = this.objectMapper.readValue(json, PersonaliaSegment.class);

        }

    @Test
    void testSerializationAndDeserialization() throws Exception {

        this.setSegment();

        assertThat(this.personaliaSegment.getFirstName()).isEqualTo("John");
        assertThat(this.personaliaSegment.getLastName()).isEqualTo("Doe");
        assertThat(this.personaliaSegment.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(this.personaliaSegment.getPhone()).isEqualTo("1234567890");
        assertThat(this.personaliaSegment.getDateOfBirth()).isEqualTo("1980-01-01T00:00:00.000Z");
        assertThat(this.personaliaSegment.getGender()).isEqualTo("Male");
        assertThat(this.personaliaSegment.getAddress()).isEqualTo("123 Main St");
        assertThat(this.personaliaSegment.getAddress2()).isEqualTo("Apt 4");
        assertThat(this.personaliaSegment.getZipCode()).isEqualTo("10001");
        assertThat(this.personaliaSegment.getCity()).isEqualTo("New York");
        assertThat(this.personaliaSegment.getCountry()).isEqualTo("USA");
        assertThat(this.personaliaSegment.getSummary()).isEqualTo("Experienced software engineer");
    
        String serializedJson = this.objectMapper.writeValueAsString(this.personaliaSegment);
        assertThat(serializedJson).contains("John");
        assertThat(serializedJson).contains("Doe");
        assertThat(serializedJson).contains("john.doe@example.com");
        assertThat(serializedJson).contains("1234567890");
        assertThat(serializedJson).contains("1980-01-01T00:00:00.000Z");
        assertThat(serializedJson).contains("Male");
        assertThat(serializedJson).contains("123 Main St");
        assertThat(serializedJson).contains("Apt 4");
        assertThat(serializedJson).contains("10001");
        assertThat(serializedJson).contains("New York");
        assertThat(serializedJson).contains("USA");
        assertThat(serializedJson).contains("Experienced software engineer");

    }
    
}
