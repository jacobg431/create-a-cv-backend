package com.cvbackend.springboot.maven.api.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class SkillsSegment extends AbstractSegment {

    @Data
    public static class Skill {

        private String skill;
        private int instanceIndex;

        private void Validate(int instanceIndex) throws Exception {
            this.instanceIndex = instanceIndex;
            ValidateSkill();
        }

        private void ValidateSkill() throws Exception {
            if (this.skill == null || this.skill.isBlank()) {
                throw new Exception("Skill not supplied for skill instance " + this.instanceIndex);
            }
        }

    }

    private List<Skill> skillList;
    private int instanceIndex;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";
        this.instanceIndex = 0;

        try {

            for (Skill skill : this.skillList) {
                skill.Validate(this.instanceIndex);
                this.instanceIndex++;
            }
            
        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

}
