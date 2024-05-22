package br.com.projetoBase.dto.clinica;

import br.com.projetoBase.modelo.DiasSemanas;

import java.time.LocalTime;
import java.util.Set;

public record ClinicaDTO(
        String nome,
        String descricao,
        Long max,
        int maxHora,
        LocalTime inicio,
        LocalTime fim,
        LocalTime inicio2,
        LocalTime fim2,
        byte[] file,
        String diasAtendimento) {

}
