package com.samuel.estudojpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table (name = "unidadesDeTrabalho")
public class UnidadeDeTrabalho {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String endereco;

    @ManyToMany (mappedBy = "unidadesDeTrabalho", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;
}
