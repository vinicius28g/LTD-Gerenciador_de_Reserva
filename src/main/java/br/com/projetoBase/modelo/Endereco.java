package br.com.projetoBase.modelo;

import jakarta.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco extends EntidadeAbstrata {
	
	@NotEmpty
	@NotNull
	private String logradouro;
	@NotNull
	private String bairro;
	@NotNull
	private String CEP;
	@NotNull
	private String numero;
	private String cidade;
	private String estado;
	
	public String getLogadouro() {
		return logradouro;
	}
	public void setLogadouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
 
}
