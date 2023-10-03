package br.com.projetoBase.modelo;

public enum TipoUsuario {

    USER("User"),
    ADMIN("Admin");

    private final String nome;

    TipoUsuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
