package com.samuel.estudojpa.repository;

import com.samuel.estudojpa.model.Funcionario;
import com.samuel.estudojpa.model.projecoes.FuncionarioProjecao;
import com.samuel.estudojpa.model.projecoes.FuncionarioProjecaoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {

    Page<Funcionario> findByNome(String nome, Pageable pageable);

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

    //projeções
    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioPorIdNomeESalario();

    @Query(value = "SELECT NEW com.samuel.estudojpa.model.projecoes.FuncionarioProjecaoDto (f.nome, f.cpf) FROM Funcionario f")
    List<FuncionarioProjecaoDto> findFuncionarioPorNomeECpf();
}
