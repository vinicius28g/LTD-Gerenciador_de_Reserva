package br.com.projetoBase.dto.clinica;

import java.time.LocalTime;

public record ClinicaDTO(String nome, String descricao, Long max, int maxHora, LocalTime inicio, LocalTime fim) {
}
