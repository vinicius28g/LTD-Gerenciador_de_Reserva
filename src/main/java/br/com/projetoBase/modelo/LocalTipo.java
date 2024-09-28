package br.com.projetoBase.modelo;

public enum LocalTipo {
	CAMPUS("Campus", 1),
	EXTERNO("Externo", 2);
	
	private final String nome;
	private final int codigo;
	
	private LocalTipo(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}
	
	

}
