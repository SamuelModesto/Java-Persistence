package com.samuel.estudojpa;

import com.samuel.estudojpa.model.Cargo;
import com.samuel.estudojpa.repository.CargoRepository;
import com.samuel.estudojpa.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EstudojpaApplication implements CommandLineRunner {

    @Autowired
    private CargoService cargoService;

    Boolean system = true;

    public static void main(String[] args) {
        SpringApplication.run(EstudojpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual ação tomar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");

            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    cargoService.menuInicial(scanner);
                    break;
                case 0:
                    system = false;
            }
        }
    }
}
