package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.repository.FuncionarioRepository;
import com.samuel.estudojpa.specifications.FuncionarioSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioSpecificationService {
    @Autowired
    FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void menuInicial(Scanner scanner) {
        System.out.println("Digite o nome:");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("null")) {
            nome = null;
        }

        System.out.println("Digite o cpf:");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("null")) {
            cpf = null;
        }

        System.out.println("Digite o salario:");
        Double salario = scanner.nextDouble();

        if (salario == 0) {
            salario = null;
        }

        System.out.println("Digite a data de contratacao:");
        String data = scanner.next();
        LocalDate dataContratacao;
        if (data.equalsIgnoreCase("null")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
                .where(
                        FuncionarioSpecification.nome(nome))
                .or(FuncionarioSpecification.cpf(cpf))
                .or(FuncionarioSpecification.salario(salario))
                .or(FuncionarioSpecification.dataContratacao(dataContratacao)
                )
        );
        funcionarios.forEach(System.out::println);

    }
}
