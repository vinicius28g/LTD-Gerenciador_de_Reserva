/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author gustavo
 */
@Entity
public class Clinica extends EntidadeAbstrata{
    @Column(unique = true)
    @NotNull
    private String nome;

    private String descricao;

    @NotNull
    private Long quantidadeMax;

    @NotNull
    private long MaxPorHorario;

    public Clinica() {

    }

    public Clinica(String nome, String descricao, Long quantidadeMax, long maxPorHorario) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeMax = quantidadeMax;
        MaxPorHorario = maxPorHorario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Clinica(String name, String descricao) {
    }

    public Long getQuantidadeMax() {
        return quantidadeMax;
    }

    public void setQuantidadeMax(Long quantidadeMax) {
        this.quantidadeMax = quantidadeMax;
    }

    public long getMaxPorHorario() {
        return MaxPorHorario;
    }

    public void setMaxPorHorario(long maxPorHorario) {
        MaxPorHorario = maxPorHorario;
    }
}

