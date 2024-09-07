package com.cvbackend.springboot.maven.api.models;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CoursesSegment {

    @Data
    public static class Course {

        private String name;
        private String instructor;
        private String completionDate;
        private String duration;
        private int instanceIndex;

        public void Validate(int instanceIndex) throws Exception {

            this.instanceIndex = instanceIndex;

            ValidateName();
            ValidateInstructor();
            ValidateCompletionDate();
            ValidateDuration();

        }

        private void ValidateName() throws Exception {
            if (this.name == null) {
                throw new Exception("Course name not supplied for course instance " + this.instanceIndex);
            }
        }
    
        private void ValidateInstructor() throws Exception {
            if (this.instructor == null) {
                throw new Exception("Instructor not supplied for course instance " + this.instanceIndex);
            }
        }
    
        private void ValidateCompletionDate() throws Exception {
            if (this.completionDate == null) {
                throw new Exception("Start date not supplied for certification instance " + this.instanceIndex);
            }
        }
    
        private void ValidateDuration() throws Exception {
            if (this.duration == null) {
                throw new Exception("Duration not supplied for certification instance " + this.instanceIndex);
            }
            if (this.duration != "Hours" || this.duration != "Days"|| this.duration != "Weeks"|| this.duration != "Months") {
                throw new Exception("Gender must be either 'Female' or 'Male' for certification instance " + this.instanceIndex);
            }
        }

    }

    private List<Course> courseList;
    private int instanceIndex;
    private Boolean isValid;
    private String validationErrorMsg;

    public Pair<Boolean, String> Validate() {

        this.isValid = true;
        this.validationErrorMsg = "";
        this.instanceIndex = 0;

        try {

            for (Course course : this.courseList) {
                course.Validate(instanceIndex);
                this.instanceIndex++;
            }

        } catch(Exception e) {
            this.isValid = false;
            this.validationErrorMsg = e.getMessage();
        }

        return new Pair<Boolean, String>(this.isValid, this.validationErrorMsg);

    }

}
