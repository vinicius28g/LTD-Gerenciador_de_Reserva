package br.com.projetoBase.dto;

public record TipoUsuarioDto(int tipoUsuario, String nomePessoa) {

	public int tipoUsuario() {
		return tipoUsuario;
	}

	public String nomePessoa() {
		return nomePessoa;
	}
	
}
