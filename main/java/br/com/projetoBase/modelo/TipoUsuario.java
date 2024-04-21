package br.com.projetoBase.modelo;

public enum TipoUsuario {

    USER(1,"User"),
    ADMIN(2,"Admin");

    private final String nome;
    private final int numero;

    

    TipoUsuario(int numero, String nome) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }
    public long getNumero() {
        return numero;
    }
    
}
