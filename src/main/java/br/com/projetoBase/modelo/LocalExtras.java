package br.com.projetoBase.modelo;

public enum LocalExtras {
	AR_CONDICIONADO("Ar_condicionado", 1),
	PROJETOR("Projetor", 2);
	
	private final String NOME;
	private final int CODIGO;
	
	
	private LocalExtras(String nOME, int cODIGO) {
		NOME = nOME;
		CODIGO = cODIGO;
	}


	public String getNOME() {
		return NOME;
	}


	public int getCODIGO() {
		return CODIGO;
	}
	
	
	

}
