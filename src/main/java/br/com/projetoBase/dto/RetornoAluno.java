package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Aluno;
import br.com.projetoBase.modelo.Usuario;

public record RetornoAluno(Usuario usuarioRetorno, Aluno aluno) {

	public Usuario usuarioRetorno() {
		return usuarioRetorno;
	}

	public Aluno aluno() {
		return aluno;
	}

}