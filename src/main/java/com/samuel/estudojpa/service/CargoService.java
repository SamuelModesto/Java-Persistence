package com.samuel.estudojpa.service;

import com.samuel.estudojpa.model.Cargo;
import com.samuel.estudojpa.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CargoService {

    private boolean system = true;

    @Autowired
    private CargoRepository cargoRepository;

    public void menuInicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar Todos");
            System.out.println("4 - Deletar Cargo1");

            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    salvarCargo(scanner);
                    break;
                case 2:
                    atualizarCargo(scanner);
                    break;
                case 3:
                    visualizarTodosOsCargos();
                    break;
                case 4:
                    deletarCargo(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    public void salvarCargo(Scanner scanner) {
        System.out.println("Descrição do cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo com sucesso!");
    }

    public void atualizarCargo(Scanner scanner) {
        System.out.println("Digite o id que deseja atualizar: ");
        Long id = scanner.nextLong();
        System.out.println("Digite a nova descrição do cargo: ");
        String novaDescricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(novaDescricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado com sucesso!");
    }

    public void visualizarTodosOsCargos(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    public void deletarCargo(Scanner scanner){
        System.out.println("Digite o id que deseja deletar: ");
        Long id = scanner.nextLong();
        cargoRepository.deleteById(id);
        System.out.println("Cargo deletado com sucesso!");
    }
}
