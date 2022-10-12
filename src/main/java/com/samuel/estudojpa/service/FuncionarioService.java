package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Cargo;
import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.model.UnidadeDeTrabalho;
import com.samuel.estudojpa.repository.CargoRepository;
import com.samuel.estudojpa.repository.FuncionarioRepository;
import com.samuel.estudojpa.repository.UnidadeDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncionarioService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UnidadeDeTrabalhoRepository unidadeTrabalhoRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de funcionario deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar Todos");
            System.out.println("4 - Deletar unidade de trabalho: ");
            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    salvarFuncionario(scanner);
                    break;
                case 2:
                    atualizarFuncionario(scanner);
                    break;
                case 3:
                    visualizarTodosOsFuncionarios(scanner);
                    break;
                case 4:
                    deletarFuncionario(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    private void salvarFuncionario(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Long idCargo = scanner.nextLong();

        List<UnidadeDeTrabalho> unidades = visitarUnidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeDeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizarFuncionario(Scanner scanner) {
        System.out.println("Digite o id");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o id do cargo");
        Long idCargo = scanner.nextLong();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        funcionario.setCargo(cargo.get());
        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario alterado com sucesso!");

    }

    private void visualizarTodosOsFuncionarios(Scanner scanner) {
        System.out.println("Digite a pagina que deseja visualizar");
        int page = scanner.nextInt();
        Pageable pageable = PageRequest.of(page, 2, Sort.unsorted());
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println(funcionarios);
        System.out.println("Pagina atual: " + funcionarios.getNumber());
        System.out.println("Quantidade total de elementos da consulta: " + funcionarios.getTotalElements());
        funcionarios.forEach(System.out::println);
    }

    private void deletarFuncionario(Scanner scanner) {
        System.out.println("Digite o id do funcionario que deseja deletar");
        Long id = scanner.nextLong();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionario Deletado");
    }

    private List<UnidadeDeTrabalho> visitarUnidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Long idUnidade = scanner.nextLong();

            if (idUnidade != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeTrabalhoRepository.findById(idUnidade);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }
}
