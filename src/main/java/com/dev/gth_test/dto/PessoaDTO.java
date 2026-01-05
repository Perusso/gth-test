package com.dev.gth_test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PessoaDTO {

    private Long id;
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;

    private String cpf;
    private char sexo;
    private Double altura;
    private Double peso;

    public PessoaDTO() {}

    public PessoaDTO(Long id, String nome, LocalDate dataNasc, String cpf, char sexo, Double altura, Double peso) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNasc() { return dataNasc; }

    public void setDataNasc(LocalDate dataNasc) { this.dataNasc = dataNasc; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public char getSexo() { return sexo; }

    public void setSexo(char sexo) { this.sexo = sexo; }

    public Double getAltura() { return altura; }

    public void setAltura(Double altura) { this.altura = altura; }

    public Double getPeso() { return peso; }

    public void setPeso(Double peso) { this.peso = peso; }
}
