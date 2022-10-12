package com.samuel.estudojpa.repository;

import com.samuel.estudojpa.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findByNomeLike(String nome);

    List<Funcionario> findByNomeEndingWith(String nome);

    List<Funcionario> findByNomeStartingWith(String nome);

    List<Funcionario> findByNomeIsNull();

    List<Funcionario> findByNomeIsNotNull();

    List<Funcionario> findByNomeOrderByDataContratacaoAsc(String nome);

    //usando join (camelCase)
    List<Funcionario> findByCargoDescricao(String descricao);

    List<Funcionario> findByUnidadesDeTrabalho_Descricao(String descricao);

    @Query("select f from Funcionario f where f.nome = :nomeArg" +
            " and f.salario >= :salarioArg and f.dataContratacao = :dataArg")
    List<Funcionario> findByNomeDataContratacaoSalario(String nomeArg, Double salarioArg, LocalDate dataArg);

    @Query(value = "select * from funcionarios f where f.data_contratacao >= :dataArg", nativeQuery = true)
    List<Funcionario> findDataContratacao(LocalDate dataArg);
}
