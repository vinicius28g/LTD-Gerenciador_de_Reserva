/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;
import java.util.Set;

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
    private int MaxPorHorario;


    private LocalTime inicio;
    private LocalTime fim;
    private LocalTime inicio2;
    private LocalTime fim2;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;


    @Column(name = "dias_atendimento")
    private String diasAtendimento;


    public Clinica() {

    }

    public Clinica(String nome, String descricao, Long quantidadeMax, int maxPorHorario) {
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

    public int getMaxPorHorario() {
        return MaxPorHorario;
    }

    public void setMaxPorHorario(int maxPorHorario) {
        MaxPorHorario = maxPorHorario;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }

    public LocalTime getInicio2() {
        return inicio2;
    }

    public void setInicio2(LocalTime inicio2) {
        this.inicio2 = inicio2;
    }

    public LocalTime getFim2() {
        return fim2;
    }

    public void setFim2(LocalTime fim2) {
        this.fim2 = fim2;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDiasAtendimento() {
        return diasAtendimento;
    }

    public void setDiasAtendimento(String diasAtendimento) {
        this.diasAtendimento = diasAtendimento;
    }
}

