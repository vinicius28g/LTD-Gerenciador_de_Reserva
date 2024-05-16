package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Clinica;

import java.time.LocalDate;

public record ConsultasByDayDTO(Clinica clinica, LocalDate date) {
}
