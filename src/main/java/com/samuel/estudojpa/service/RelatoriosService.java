package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.model.projecoes.FuncionarioProjecao;
import com.samuel.estudojpa.model.projecoes.FuncionarioProjecaoDto;
import com.samuel.estudojpa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair.");
            System.out.println("1 - Pesquisar funcionario pelo nome.");
            System.out.println("2 - Pesquisar funcionario por filtros.");
            System.out.println("3 - Pesquisar funcionario pela data de contratacao.");
            System.out.println("4 - Pesquisar funcionario por parte do nome.");
            System.out.println("5 - Pesquisar funcionario por parte final do nome.");
            System.out.println("6 - Pesquisar funcionario por parte inicial do nome.");
            System.out.println("7 - Pesquisar funcionarios com nome nulo.");
            System.out.println("8 - Pesquisar funcionarios com nome Não nulo.");
            System.out.println("9 - Pesquisar funcionarios por nome e ordenar por data de contratacao.");
            System.out.println("10 - Pesquisar funcionarios por cargo.");
            System.out.println("11 - Pesquisar funcionarios por unidade de trabalho.");
            System.out.println("12 - Pesquisar funcionarios com filtro de id, nome e salario.");
            System.out.println("13 - Pesquisar funcionarios com filtro de nome e cpf.");

            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    buscarFuncionarioPeloNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioPorFiltros(scanner);
                    break;
                case 3:
                    buscarFuncionarioPelaDataDeContratacao(scanner);
                    break;
                case 4:
                    buscarFuncionarioPorParteDoNome(scanner);
                    break;
                case 5:
                    buscarFuncionarioQueTerminaCom(scanner);
                    break;
                case 6:
                    buscarFuncionarioQueComecaCom(scanner);
                    break;
                case 7:
                    buscarFuncionarioComNomeNulo();
                    break;
                case 8:
                    buscarFuncionarioComNomeNaoNulo();
                    break;
                case 9:
                    buscarFuncionarioPorNomeEOrdenarPorDataDeContratacao(scanner);
                    break;
                case 10:
                    buscarFuncionarioPorCargo(scanner);
                    break;
                case 11:
                    buscarFuncionarioPorUnidadeDeTrabalho(scanner);
                    break;
                case 12:
                    buscarTodosOsFuncionariosComFiltroDeIdNomeESalario();
                    break;
                case 13:
                    buscarTodosOsFuncionariosComFiltroDeNomeECpf();
                    break;
                default:
                    system = false;
            }
        }
    }

    public void buscarFuncionarioPeloNome(Scanner scanner) {
        System.out.println("Digite o nome que deseja pesquisar: ");
        String nome = scanner.next();
        System.out.println("Digite a pagina que deseja visualizar");
        int page = scanner.nextInt();
        Pageable pageable = PageRequest.of(page, 2, Sort.unsorted());
        Page<Funcionario> funcionarios = funcionarioRepository.findByNome(nome, pageable);
        System.out.println(funcionarios);
        System.out.println("Pagina atual: " + funcionarios.getNumber());
        System.out.println("Quantidade total de elementos da consulta: " + funcionarios.getTotalElements());
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPorParteDoNome(Scanner scanner){
        System.out.println("Digite parte do nome que deseja pesquisar: ");
        String nome = "%"+scanner.next()+"%";
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeLike(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioQueTerminaCom(Scanner scanner){
        System.out.println("Digite parte final do nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeEndingWith(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioQueComecaCom(Scanner scanner){
        System.out.println("Digite parte inicial do nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeStartingWith(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioComNomeNulo(){
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeIsNull();
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioComNomeNaoNulo(){
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeIsNotNull();
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPorNomeEOrdenarPorDataDeContratacao(Scanner scanner){
        System.out.println("Digite o nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNomeOrderByDataContratacaoAsc(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPorCargo(Scanner scanner){
        System.out.println("Digite o cargo: ");
        String cargo = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByCargoDescricao(cargo);
        funcionarios.forEach(System.out::println);
    }
    public void buscarFuncionarioPorUnidadeDeTrabalho(Scanner scanner){
        System.out.println("Digite a descricao da unidade de trabalho: ");
        String descricao = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByUnidadesDeTrabalho_Descricao(descricao);
        funcionarios.forEach(System.out::println);
    }


    public void buscarFuncionarioPorFiltros(Scanner scanner) {
        System.out.println("Qual o nome do funcionário que deseja pesquisar: ");
        String nome = scanner.next();
        System.out.println("Qual salário deseja pesquisar: ");
        Double salario = scanner.nextDouble();
        System.out.println("Qual a data de contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findByNomeDataContratacaoSalario(nome, salario, localDate);
        funcionarios.forEach(System.out::println);
    }

    public void buscarFuncionarioPelaDataDeContratacao(Scanner scanner) {
        System.out.println("Qual a data de contratacao: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacao(localDate);
        funcionarios.forEach(System.out::println);
    }

    public void buscarTodosOsFuncionariosComFiltroDeIdNomeESalario() {
        List<FuncionarioProjecao> funcionarios = funcionarioRepository.findFuncionarioPorIdNomeESalario();
        funcionarios.forEach(e -> System.out.println(" id: " + e.getId() + " nome: " + e.getNome() + " salario: " + e.getSalario()));
    }

    public void buscarTodosOsFuncionariosComFiltroDeNomeECpf() {
        List<FuncionarioProjecaoDto> funcionarios = funcionarioRepository.findFuncionarioPorNomeECpf();
        funcionarios.forEach(e -> System.out.println(" nome: " + e.getNome() + " cpf: " + e.getCpf()));
    }

}
