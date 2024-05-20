package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultasDTO(Long id, Usuario paciente,Usuario funcionario, Clinica clinica, LocalDate dia, LocalTime inicio, LocalTime fim, String info) {
}
