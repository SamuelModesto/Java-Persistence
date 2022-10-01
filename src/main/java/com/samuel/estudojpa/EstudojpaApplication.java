package com.samuel.estudojpa;

import com.samuel.estudojpa.model.Cargo;
import com.samuel.estudojpa.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudojpaApplication implements CommandLineRunner {

	final
	CargoRepository repository;

	public EstudojpaApplication(CargoRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(EstudojpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setDescricao("DESENVOLVEDOR DE SOFTWARE");
		repository.save(cargo);
	}
}
