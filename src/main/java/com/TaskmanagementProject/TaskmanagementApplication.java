package com.TaskmanagementProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.TaskmanagementProject.Entity") // Tells Spring where entities are
@EnableJpaRepositories(basePackages= "com.TaskmanagementProject.Repository") // Tells Spring where repos are
@EnableFeignClients(basePackages = "com.Taskmanagement.Client") 
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
