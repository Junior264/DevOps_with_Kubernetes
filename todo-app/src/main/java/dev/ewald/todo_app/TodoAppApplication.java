package dev.ewald.todo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TodoAppApplication.class, args);

		System.out.println("Server is running on port " + context.getEnvironment().getProperty("server.port"));
	}
}
