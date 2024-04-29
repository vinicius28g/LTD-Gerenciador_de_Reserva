package br.com.projetoBase.dto;


public record UsuarioCadastro(String user, String pass, String nome) {
    public String user() {
        return user;
    }

    public String pass() {
        return pass;
    }

    public String nome() {
        return nome;
    }
}
