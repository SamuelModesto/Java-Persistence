package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean system = true;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Pesquisar funcionario pelo nome");


            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    buscarFuncionarioPeloNome(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    public void buscarFuncionarioPeloNome(Scanner scanner){
        System.out.println("Digite o nome que deseja pesquisar: ");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

}
