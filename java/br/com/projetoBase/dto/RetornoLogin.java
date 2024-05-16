package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.TipoUsuario;

public record RetornoLogin(String nome, TipoUsuario tipoUsuario, String token){
}
