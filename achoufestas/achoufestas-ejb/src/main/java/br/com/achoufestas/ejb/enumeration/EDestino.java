package br.com.achoufestas.ejb.enumeration;

public enum EDestino {
	
	HOME("home","Página inicial");
	
	private final String destino;
	private final String descricao;
	
	private EDestino(String destino, String descricao) {
		this.destino = destino;
		this.descricao = descricao;
	}

	public String getDestino() {
		return destino;
	}

	public String getDescricao() {
		return descricao;
	}

	
}