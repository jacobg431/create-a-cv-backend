package com.cvbackend.springboot.maven.api.models;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class CoursesSegment extends AbstractSegment {

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
            if (this.name == null || this.name.isBlank()) {
                throw new Exception("Course name not supplied for course instance " + this.instanceIndex);
            }
        }
    
        private void ValidateInstructor() throws Exception {
            if (this.instructor == null || this.instructor.isBlank()) {
                throw new Exception("Instructor not supplied for course instance " + this.instanceIndex);
            }
        }
    
        private void ValidateCompletionDate() throws Exception {
            if (this.completionDate == null || this.completionDate.isBlank()) {
                throw new Exception("Start date not supplied for certification instance " + this.instanceIndex);
            }
        }
    
        private void ValidateDuration() throws Exception {
            if (this.duration == null || this.duration.isBlank()) {
                throw new Exception("Duration not supplied for certification instance " + this.instanceIndex);
            }
            if (!this.duration.equals("Hours") && 
                !this.duration.equals("Days") && 
                !this.duration.equals("Weeks") && 
                !this.duration.equals("Months")) {
                throw new Exception("Duration input not valid for certification instance " + this.instanceIndex);
            }
        }

    }

    private List<Course> courseList;
    private int instanceIndex;

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
