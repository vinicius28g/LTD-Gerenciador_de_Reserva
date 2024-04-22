package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.TipoUsuario;

public record UsuarioCadastro(String nome,
                              String user,
                              String pass,
                              TipoUsuario tipoUsuario) {
}
