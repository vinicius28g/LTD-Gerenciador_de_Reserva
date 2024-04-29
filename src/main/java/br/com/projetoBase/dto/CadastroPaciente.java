package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Paciente;

public record CadastroPaciente(String user, String pass, Paciente paciente) {

	public String user() {
		return user;
	}

	public String pass() {
		return pass;
	}

	public Paciente paciente() {
		return paciente;
	}

}
