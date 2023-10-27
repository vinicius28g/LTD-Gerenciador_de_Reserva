package br.com.projetoBase.modelo;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
@Entity
public class Questionario extends EntidadeAbstrata{
	private String situacaoAtual;
	private String meioSustento;
	private String historiaEmprego;
	private String nivelEscolaridade;
	private String motivoConsulta;
	private String outrosMotivosConsulta;
	private String nivelHumor;
	private String diagnosticoConsulta;
	private String medicacao;
	private String usoSubstancias;
	private String sofreuViolencia;
	private String programaComunitario;
	private String horasVagas;
	private String historicoProblemas;
	private String membroFamiliar;
	private String apoioFamiliar;
	private String maisAlgumacoisa;
	private String adjetivoDescricao;
	private String expectativas;
	private String obeservacao;
	
	@ManyToOne
	private UsuarioPaciente usuariopaciente;
	
	public UsuarioPaciente getUsuariopaciente() {
		return usuariopaciente;
	}
	public void setUsuariopaciente(UsuarioPaciente usuariopaciente) {
		this.usuariopaciente = usuariopaciente;
	}
	public String getSituacaoAtual() {
		return situacaoAtual;
	}
	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}
	public String getMeioSustento() {
		return meioSustento;
	}
	public void setMeioSustento(String meioSustento) {
		this.meioSustento = meioSustento;
	}
	public String getHistoriaEmprego() {
		return historiaEmprego;
	}
	public void setHistoriaEmprego(String historiaEmprego) {
		this.historiaEmprego = historiaEmprego;
	}
	public String getNivelEscolaridade() {
		return nivelEscolaridade;
	}
	public void setNivelEscolaridade(String nivelEscolaridade) {
		this.nivelEscolaridade = nivelEscolaridade;
	}
	public String getMotivoConsulta() {
		return motivoConsulta;
	}
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	public String getOutrosMotivosConsulta() {
		return outrosMotivosConsulta;
	}
	public void setOutrosMotivosConsulta(String outrosMotivosConsulta) {
		this.outrosMotivosConsulta = outrosMotivosConsulta;
	}
	public String getNivelHumor() {
		return nivelHumor;
	}
	public void setNivelHumor(String nivelHumor) {
		this.nivelHumor = nivelHumor;
	}
	public String getDiagnosticoConsulta() {
		return diagnosticoConsulta;
	}
	public void setDiagnosticoConsulta(String diagnosticoConsulta) {
		this.diagnosticoConsulta = diagnosticoConsulta;
	}
	public String getMedicacao() {
		return medicacao;
	}
	public void setMedicacao(String medicacao) {
		this.medicacao = medicacao;
	}
	public String getUsoSubstancias() {
		return usoSubstancias;
	}
	public void setUsoSubstancias(String usoSubstancias) {
		this.usoSubstancias = usoSubstancias;
	}
	public String getSofreuViolencia() {
		return sofreuViolencia;
	}
	public void setSofreuViolencia(String sofreuViolencia) {
		this.sofreuViolencia = sofreuViolencia;
	}
	public String getProgramaComunitario() {
		return programaComunitario;
	}
	public void setProgramaComunitario(String programaComunitario) {
		this.programaComunitario = programaComunitario;
	}
	public String getHorasVagas() {
		return horasVagas;
	}
	public void setHorasVagas(String horasVagas) {
		this.horasVagas = horasVagas;
	}
	public String getHistoricoProblemas() {
		return historicoProblemas;
	}
	public void setHistoricoProblemas(String historicoProblemas) {
		this.historicoProblemas = historicoProblemas;
	}
	public String getMembroFamiliar() {
		return membroFamiliar;
	}
	public void setMembroFamiliar(String membroFamiliar) {
		this.membroFamiliar = membroFamiliar;
	}
	public String getApoioFamiliar() {
		return apoioFamiliar;
	}
	public void setApoioFamiliar(String apoioFamiliar) {
		this.apoioFamiliar = apoioFamiliar;
	}
	public String getMaisAlgumacoisa() {
		return maisAlgumacoisa;
	}
	public void setMaisAlgumacoisa(String maisAlgumacoisa) {
		this.maisAlgumacoisa = maisAlgumacoisa;
	}
	public String getAdjetivoDescricao() {
		return adjetivoDescricao;
	}
	public void setAdjetivoDescricao(String adjetivoDescricao) {
		this.adjetivoDescricao = adjetivoDescricao;
	}
	public String getExpectativas() {
		return expectativas;
	}
	public void setExpectativas(String expectativas) {
		this.expectativas = expectativas;
	}
	public String getObeservacao() {
		return obeservacao;
	}
	public void setObeservacao(String obeservacao) {
		this.obeservacao = obeservacao;
	}
	
}
