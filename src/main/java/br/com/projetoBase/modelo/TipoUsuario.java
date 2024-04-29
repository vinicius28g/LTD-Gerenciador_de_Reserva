package br.com.projetoBase.modelo;

import org.apache.catalina.User;

import br.com.projetoBase.Constates.ConstantesSistema;

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
    public static TipoUsuario getTipo(int codigo) {
    	for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException(ConstantesSistema.ENUM_INVALIDO);
    	
    }

	public Class<?> getClasse() {
		return classe;
	}
    

}
