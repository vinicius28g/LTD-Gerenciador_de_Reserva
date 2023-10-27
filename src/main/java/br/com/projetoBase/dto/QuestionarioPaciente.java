package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Questionario;
import br.com.projetoBase.modelo.UsuarioPaciente;

public record QuestionarioPaciente (Questionario questionario, UsuarioPaciente usuarioPaciente) {

}
