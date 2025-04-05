package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	private static int pacienteCounter = 0;
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	public static void incrementarContadorPacientes() {
        pacienteCounter++;
    }

    public static int getPacienteCounter() {
        return pacienteCounter;
    }
}
