package br.com.projetoBase.modelo;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Evento extends EntidadeAbstrata {
	private String nome;
	private String descicao;
	private int capacidade;
	@ManyToMany
	@JoinTable(
			name="usuario_evento",
			joinColumns = @JoinColumn( name="usuario_id"),
			inverseJoinColumns=  @JoinColumn(name= "evento_id"))
	private Set<Usuario> gerenciadores = new HashSet<>();
	@ManyToOne
	private Local local;
	
	private LocalTime reservaTempo;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCriacao;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataAtualizacao;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDelete;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescicao() {
		return descicao;
	}
	public void setDescicao(String descicao) {
		this.descicao = descicao;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public Set<Usuario> getGerenciadores() {
		return gerenciadores;
	}
	public void setGerenciadores(Set<Usuario> gerenciadores) {
		this.gerenciadores = gerenciadores;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public LocalTime getReservaTempo() {
		return reservaTempo;
	}
	public void setReservaTempo(LocalTime reservaTempo) {
		this.reservaTempo = reservaTempo;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public Date getDataDelete() {
		return dataDelete;
	}
	public void setDataDelete(Date dataDelete) {
		this.dataDelete = dataDelete;
	}
	
	

}
