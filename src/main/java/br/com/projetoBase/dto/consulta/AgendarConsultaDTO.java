package br.com.projetoBase.dto.consulta;

import br.com.projetoBase.modelo.Clinica;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendarConsultaDTO(LocalDate dia,Clinica clinica,
                                 LocalTime horaInicio, LocalTime  horaFim, String paciente,
                                 String info) {
}

