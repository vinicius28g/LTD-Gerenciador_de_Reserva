package br.com.projetoBase.dto;


public record UsuarioCadastro(String user, String password, String nome, String clinica) {
    public String user() {
        return user;
    }

    public String pass() {
        return password;
    }

    public String nome() {
        return nome;
    }
}
