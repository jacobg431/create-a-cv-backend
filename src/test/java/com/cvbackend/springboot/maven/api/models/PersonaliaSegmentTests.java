package com.cvbackend.springboot.maven.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PersonaliaSegmentTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializationAndDeserialization() throws Exception {
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

        PersonaliaSegment segment = objectMapper.readValue(json, PersonaliaSegment.class);
        assertThat(segment.getFirstName()).isEqualTo("John");
        assertThat(segment.getLastName()).isEqualTo("Doe");
        assertThat(segment.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(segment.getPhone()).isEqualTo("1234567890");
        assertThat(segment.getDateOfBirth()).isEqualTo("1980-01-01T00:00:00.000Z");
        assertThat(segment.getGender()).isEqualTo("Male");
        assertThat(segment.getAddress()).isEqualTo("123 Main St");
        assertThat(segment.getAddress2()).isEqualTo("Apt 4");
        assertThat(segment.getZipCode()).isEqualTo("10001");
        assertThat(segment.getCity()).isEqualTo("New York");
        assertThat(segment.getCountry()).isEqualTo("USA");
        assertThat(segment.getSummary()).isEqualTo("Experienced software engineer");
    
        String serializedJson = objectMapper.writeValueAsString(segment);
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
