package br.ce.wcaquino.taskbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.ce.wcaquino.taskbackend.repo")
@SpringBootApplication
public class TaskBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaskBackendApplication.class, args);
	}

}
