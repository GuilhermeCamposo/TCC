package br.com.achoufestas.ejb.enumeration;

public enum SessionKeys {
	
	USUARIO("usuario");
	
	private final String chave;
	
	private SessionKeys(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}

}