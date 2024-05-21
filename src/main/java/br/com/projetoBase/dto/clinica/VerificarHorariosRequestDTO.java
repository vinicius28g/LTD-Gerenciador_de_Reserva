package br.com.projetoBase.dto.clinica;

import br.com.projetoBase.modelo.Clinica;

import java.time.LocalDate;


public record VerificarHorariosRequestDTO(Clinica clinica, LocalDate data) {
}
