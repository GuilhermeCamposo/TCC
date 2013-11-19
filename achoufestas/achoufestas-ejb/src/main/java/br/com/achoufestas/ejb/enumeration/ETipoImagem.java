package br.com.achoufestas.ejb.enumeration;

public enum ETipoImagem {
	
	M("MINI","Mini"),
	N("NORMAL","Normal");	
	
	private final String codigo;
	private final String label;
	
	private ETipoImagem(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLabel() {
		return label;
	}
	
}