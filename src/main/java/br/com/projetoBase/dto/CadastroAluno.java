/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoBase.dto;

import br.com.projetoBase.modelo.Aluno;

/**
 *
 * @author gustavo
 */
public record CadastroAluno(String user, String pass, Aluno aluno){
    public String user() {
		return user;
	}

	public String pass() {
		return pass;
	}

	public Aluno aluno() {
		return aluno;
	}

}
