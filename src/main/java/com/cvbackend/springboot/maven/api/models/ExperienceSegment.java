package com.cvbackend.springboot.maven.api.models;

import lombok.Data;

@Data
public class ExperienceSegment {
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    private Boolean isWorking;
    private String description;
}
