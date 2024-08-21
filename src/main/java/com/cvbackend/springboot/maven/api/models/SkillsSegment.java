package com.cvbackend.springboot.maven.api.models;

import java.util.List;
import lombok.Data;

@Data
public class SkillsSegment {
    private List<Skill> skills;
    private String input;

    @Data
    public static class Skill {
        private String skill;
    }
}
