package com.samuel.estudojpa.model.projecoes;

public class FuncionarioProjecaoDto {

    private String nome;
    private String cpf;

    //Parametros do construtor devem ser na mesma ordem da JPQL.
    public FuncionarioProjecaoDto(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
