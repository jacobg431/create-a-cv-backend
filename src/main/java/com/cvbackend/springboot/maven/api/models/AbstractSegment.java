package com.cvbackend.springboot.maven.api.models;

import org.javatuples.Pair;

public abstract class AbstractSegment {

    protected Boolean isValid;
    protected String validationErrorMsg;

    public abstract Pair<Boolean, String> Validate();
    
}
