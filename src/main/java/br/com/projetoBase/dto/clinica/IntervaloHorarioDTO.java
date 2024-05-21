package br.com.projetoBase.dto.clinica;

import java.time.LocalTime;

public record IntervaloHorarioDTO(LocalTime inicio, LocalTime fim, int quantidadeMax) {
}
