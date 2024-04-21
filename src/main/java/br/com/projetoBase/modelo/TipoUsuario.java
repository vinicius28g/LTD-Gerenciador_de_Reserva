package br.com.projetoBase.modelo;

import org.apache.catalina.User;

public enum TipoUsuario {

    ADMIN("Admin", 1, User.class),
    PACIENTE("Paciente", 2, Paciente.class );

    private final String nome;
    private final int codigo;
    private final Class<?> classe;

    TipoUsuario(String nome, int codigo, Class<?> classe) {
        this.nome = nome;
        this.codigo = codigo;
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }
    public int getCodigo() {
    	return codigo;
    }

	public Class<?> getClasse() {
		return classe;
	}
    

}
;