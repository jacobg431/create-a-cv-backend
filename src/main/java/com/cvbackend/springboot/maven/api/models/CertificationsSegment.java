package com.cvbackend.springboot.maven.api.models;

import lombok.Data;

@Data
public class CertificationsSegment {
    private String name;
    private String issuer;
    private String startDate;
    private String endDate;
    private Boolean isNotExpiring;
}
