package br.com.projetoBase.dto;


import br.com.projetoBase.modelo.TipoUsuario;

public record UsuarioCadastro(String user, String pass, TipoUsuario tipoUsuario, String nome) {
    public String user() {
        return user;
    }

    public String pass() {
        return pass;
    }

    public TipoUsuario tipoUsuario() {
        return tipoUsuario;
    }

    public String nome() {
        return nome;
    }
}
