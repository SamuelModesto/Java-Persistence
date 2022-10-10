package com.samuel.estudojpa.model;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private double salario;
    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades", joinColumns = {
            @JoinColumn(name = "fk_funcionario")
    }, inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
    private List<UnidadeDeTrabalho> unidadesDeTrabalho;


    public void setUnidadeDeTrabalhos(List<UnidadeDeTrabalho> unidades) {
        this.unidadesDeTrabalho = unidades;
    }
}
