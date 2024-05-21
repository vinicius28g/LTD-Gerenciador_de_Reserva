package br.com.projetoBase.dto.consulta;

import br.com.projetoBase.modelo.Clinica;
import br.com.projetoBase.modelo.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultasDTO(Long id, String paciente,String funcionario, Clinica clinica, LocalDate dia, LocalTime inicio, LocalTime fim, String info) {
}
