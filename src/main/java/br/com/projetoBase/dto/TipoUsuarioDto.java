package br.com.projetoBase.dto;

public record TipoUsuarioDto(int tipoUsuario, long usuarioId) {

	public int tipoUsuario() {
		return tipoUsuario;
	}

	public long usuarioId() {
		return usuarioId;
	}


	
}
