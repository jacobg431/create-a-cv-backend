package com.cvbackend.springboot.maven.api.models;

import lombok.Data;
import java.util.Map;

@Data
public class PersonaliaSegment {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String address2;
    private String zipCode;
    private String city;
    private String country;
    private String summary;
    private Map<String, Object> profilePicture;
}
