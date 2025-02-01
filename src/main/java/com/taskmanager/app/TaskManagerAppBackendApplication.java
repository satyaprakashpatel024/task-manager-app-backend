package com.taskmanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TaskManagerAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerAppBackendApplication.class, args);
	}

	@GetMapping("")
	public String helloWorld() {
        return "Hello, Task Manager App!";
    }

}
