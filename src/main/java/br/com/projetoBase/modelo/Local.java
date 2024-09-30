package br.com.projetoBase.modelo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
@Entity
public class Local extends EntidadeAbstrata{
	
	private int capacidade;
	
	private List<String> recursos;
	
	@Enumerated(EnumType.STRING)
	private LocalTipo localTipo;
	
	private String localDetalhes;
	
	private LocalExtras localExtras;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date DataCriacao;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataAtualizacao;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataExclusao;
	
	@OneToMany(mappedBy = "local")
	private Set<Evento> eventos;

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public List<String> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<String> recursos) {
		this.recursos = recursos;
	}

	public LocalTipo getLocalTipo() {
		return localTipo;
	}

	public void setLocalTipo(LocalTipo localTipo) {
		this.localTipo = localTipo;
	}

	public String getLocalDetalhes() {
		return localDetalhes;
	}

	public void setLocalDetalhes(String localDetalhes) {
		this.localDetalhes = localDetalhes;
	}

	public LocalExtras getLocalExtras() {
		return localExtras;
	}

	public void setLocalExtras(LocalExtras localExtras) {
		this.localExtras = localExtras;
	}

	public Date getDataCriacao() {
		return DataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		DataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	
}
