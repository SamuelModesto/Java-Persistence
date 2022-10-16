package com.samuel.estudojpa.specifications;

import com.samuel.estudojpa.model.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FuncionarioSpecification {

    public static Specification<Funcionario> nome(String nomeArg) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nomeArg + "%");
    }

    public static Specification<Funcionario> cpf(String cpfArg) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpfArg );
    }

    public static Specification<Funcionario> salario(Double salarioArg) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"), salarioArg);
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacaoArg) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacaoArg);
    }

}
