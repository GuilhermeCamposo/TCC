package br.com.achoufestas.ejb.enumeration;

public enum EStatus {
	
	INATIVO(0,"Inativo"),
	ATIVO(1,"Ativo");	
	
	private final Integer codigo;
	private final String label;
	
	private EStatus(Integer codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getLabel() {
		return label;
	}
	
}