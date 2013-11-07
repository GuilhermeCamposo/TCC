package br.com.achoufestas.lib.entidades;

import java.io.Serializable;
import java.util.List;

public class UsuarioApp  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6809910057210952790L;
	private Long idUsuario;
	private String nome, senha, email;
	private List<EventoApp> eventosMarcados;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EventoApp> getEventosMarcados() {
		return eventosMarcados;
	}

	public void setEventosMarcados(List<EventoApp> eventosMarcados) {
		this.eventosMarcados = eventosMarcados;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}