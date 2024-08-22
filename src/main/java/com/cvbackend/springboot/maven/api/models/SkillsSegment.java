package com.cvbackend.springboot.maven.api.models;

import java.util.List;
import lombok.Data;

@Data
public class SkillsSegment {
    private List<Skill> skills;

    @Data
    public static class Skill {
        private String skill;
    }
}
