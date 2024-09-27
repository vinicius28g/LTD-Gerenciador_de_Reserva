package br.com.projetoBase.modelo;

import org.apache.catalina.User;

import br.com.projetoBase.Constates.ConstantesSistema;

public enum TipoUsuario {

	COORDENADOR("Coordenador", 1),
	PROFESSOR("Professor", 2),
	ALUNO("Aluno",3);
	

    private final String nome;
    private final int codigo;
  

    TipoUsuario(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
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
}
