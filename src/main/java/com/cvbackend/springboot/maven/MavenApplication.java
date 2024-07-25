package com.cvbackend.springboot.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EntityScan("com.web")
@ComponentScan(basePackages = {"com.web"})
@ComponentScan(basePackageClasses = FrontendController.class)
@ComponentScan(basePackageClasses = BackendController.class)
public class MavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenApplication.class, args);
	}

}
