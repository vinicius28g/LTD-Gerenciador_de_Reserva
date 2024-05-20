package br.com.projetoBase.dto;

import java.util.Date;

public record UsuarioCadastro(long id,String user, String pass, String nome, String telefone,  Date DataNascimento, String cpf) {
    
	public long id() {
		return id;
	}

	public String user() {
        return user;
    }

    public String pass() {
        return pass;
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
