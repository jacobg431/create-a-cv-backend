package com.cvbackend.springboot.maven.api.models;

import java.util.List;

import org.javatuples.Pair;

import lombok.Data;

@Data
public class SkillsSegment {
    private List<Skill> skills;

    @Data
    public static class Skill {
        private String skill;
        private int instanceIndex;

        public void ValidateSkill(int instanceIndex) throws Exception {
            this.instanceIndex = instanceIndex;
            if (this.skill == null) {
                throw new Exception("Skill not supplied for skill instance " + this.instanceIndex);
            }
        }
    }

    private Boolean isValid;
    private String validationErrorMsg;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";

        try {

            ValidateSkills();
            
        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

    private void ValidateSkills() throws Exception {
        int instanceIndex = 0;
        for (Skill skill : skills) {
            skill.ValidateSkill(instanceIndex);
            instanceIndex++;
        }
    }

}
