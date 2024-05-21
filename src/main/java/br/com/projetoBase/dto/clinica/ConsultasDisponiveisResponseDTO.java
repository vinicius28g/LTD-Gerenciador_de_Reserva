package br.com.projetoBase.dto.clinica;

import java.util.List;

public record ConsultasDisponiveisResponseDTO(int limite, List<IntervaloHorarioDTO> intervaloHorarioDTOS) {
}
