package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Paciente;
import br.com.projetoBase.modelo.Usuario;

public record RetornoPaciente(Usuario usuarioRetorno, Paciente paciente) {

	public Usuario usuarioRetorno() {
		return usuarioRetorno;
	}

	public Paciente paciente() {
		return paciente;
	}

}
