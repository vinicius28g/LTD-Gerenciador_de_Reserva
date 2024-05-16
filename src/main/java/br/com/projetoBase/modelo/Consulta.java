package br.com.projetoBase.modelo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Consulta extends EntidadeAbstrata{

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @NotNull
    private Usuario paciente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Usuario funcionario;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;


    @NotNull
    private LocalDate diaSemana;

    @NotNull
    private LocalTime horarioInicio;
    @NotNull
    private LocalTime horarioFim;

    private String informacoesGerais;

    public Consulta () {

    }

    public Consulta(Usuario paciente, Usuario funcionario, Clinica clinica, LocalDate diaSemana, LocalTime horarioInicio, LocalTime horarioFim, String informacoesGerais) {
        this.paciente = paciente;
        this.funcionario = funcionario;
        this.clinica = clinica;
        this.diaSemana = diaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.informacoesGerais = informacoesGerais;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public LocalDate getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(LocalDate diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getInformacoesGerais() {
        return informacoesGerais;
    }

    public void setInformacoesGerais(String informacoesGerais) {
        this.informacoesGerais = informacoesGerais;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }
}
