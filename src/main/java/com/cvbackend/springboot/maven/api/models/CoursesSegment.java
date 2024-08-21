package com.cvbackend.springboot.maven.api.models;

import lombok.Data;

@Data
public class CoursesSegment {
    private String name;
    private String instructor;
    private String completionDate;
    private String duration;
}
