package com.cvbackend.springboot.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.cvbackend.springboot.maven.api.controllers.CvController;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EntityScan("com.web")
@ComponentScan(basePackages = {"com.web", "com.cvbackend.springboot.maven.api.models"})
@ComponentScan(basePackageClasses = CvController.class)
public class MavenApplication {
	public static void main(String[] args) {
		SpringApplication.run(MavenApplication.class, args);
	}
}
