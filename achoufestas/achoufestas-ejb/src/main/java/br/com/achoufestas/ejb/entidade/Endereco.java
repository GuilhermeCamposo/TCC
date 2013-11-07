package br.com.achoufestas.ejb.entidade;

import javax.persistence.Column;

public class Endereco {
	
	@Column(name = "logradouro", length=250)
	private String logradouro;
	
	@Column(name = "complemento", length=50)
	private String complemento;
	
	@Column(name = "cep", length=50)
	private String cep;
	
	@Column(name = "bairro", length=50)
	private String bairro;
	
	@Column(name = "cidade", length=50)
	private String cidade;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "latitude")
	private String latitude;

}
