package com.cvbackend.springboot.maven.api.models;

import java.util.List;
import org.javatuples.Pair;
import lombok.Data;

@Data
public class SkillsSegment {

    @Data
    public class Skill {

        private String skill;
        private int instanceIndex;

        private void Validate(int instanceIndex) throws Exception {
            this.instanceIndex = instanceIndex;
            ValidateSkill();
        }

        private void ValidateSkill() throws Exception {
            if (this.skill == null) {
                throw new Exception("Skill not supplied for skill instance " + this.instanceIndex);
            }
        }

    }

    private List<Skill> skillList;
    private Boolean isValid;
    private String validationErrorMsg;
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
