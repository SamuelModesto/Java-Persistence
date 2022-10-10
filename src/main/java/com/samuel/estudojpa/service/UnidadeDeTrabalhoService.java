package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.UnidadeDeTrabalho;
import com.samuel.estudojpa.repository.UnidadeDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UnidadeDeTrabalhoService {

    private boolean system = true;

    @Autowired
    private UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de unidade de trabalho deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar Todos");
            System.out.println("4 - Deletar unidade de trabalho: ");
            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    salvarUnidadeDeTrabalho(scanner);
                    break;
                case 2:
                    atualizarDescricaoDaUnidadeDeTrabalho(scanner);
                    break;
                case 3:
                    visualizarTodasAsUnidadesDeTrabalho();
                    break;
                case 4:
                    deletarUnidadeDeTrabalho(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    private void salvarUnidadeDeTrabalho(Scanner scanner) {
        System.out.println("Descrição da unidade de trabalho: ");
        String descricao = scanner.next();
        System.out.println("Endereco da unidade de trabalho: ");
        String endereco = scanner.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setDescricao(descricao);
        unidadeDeTrabalho.setEndereco(endereco);
        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Unidade de trabalho salvo com sucesso");
    }

    private void atualizarDescricaoDaUnidadeDeTrabalho(Scanner scanner) {
        System.out.println("Digite o id que deseja atualizar: ");
        Long id = scanner.nextLong();
        System.out.println("Digite a nova descrição da unidade de trabalho: ");
        String novaDescricao = scanner.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setId(id);
        unidadeDeTrabalho.setDescricao(novaDescricao);
        unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Unidade de trabalho atualizado com sucesso!");
    }

    public void visualizarTodasAsUnidadesDeTrabalho() {
        Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
        unidades.forEach(cargo -> System.out.println(unidades));
    }

    private void deletarUnidadeDeTrabalho(Scanner scanner) {
        System.out.println("Digite o id que deseja deletar: ");
        Long id = scanner.nextLong();
        unidadeDeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de trabalho deletada com sucesso!");
    }
}