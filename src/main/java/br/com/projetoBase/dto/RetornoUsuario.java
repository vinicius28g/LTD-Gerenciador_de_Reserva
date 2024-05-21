package br.com.projetoBase.dto;

import java.util.Date;

public record RetornoUsuario(long id,String user,String tipoUsuario, String nome, String telefone,  Date DataNascimento) {
    
	public long id() {
		return id;
	}

	public String user() {
        return user;
    }
	
    public String tipoUsuario() {
		return tipoUsuario;
	}

	public String nome() {
        return nome;
    }

	public String telefone() {
		return telefone;
	}

	public Date DataNascimento() {
		return DataNascimento;
	}
}