package com.youraf.notebook;

import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotebookServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookServerAppApplication.class, args);
	}
	
	@Bean
	public PythonInterpreter embededPythonInterpreter() {
		return new PythonInterpreter();
	}
	

}
