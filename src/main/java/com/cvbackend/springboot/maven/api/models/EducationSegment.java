package com.cvbackend.springboot.maven.api.models;

import lombok.Data;

@Data
public class EducationSegment {
    private String school;
    private String degree;
    private String startDate;
    private String endDate;
    private Boolean isStudying;
}
