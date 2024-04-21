package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.TipoUsuario;

public record UsuarioRetorno(String nome, TipoUsuario tipoUsuario, String token){
}
