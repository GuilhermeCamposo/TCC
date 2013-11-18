package br.com.achoufestas.lib.messages;

import java.io.Serializable;

import br.com.achoufestas.lib.entidades.UsuarioApp;

public class DefaultMessage implements Serializable {

	private String erro;
	private UsuarioApp usuario;
	
	public UsuarioApp getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioApp usuario) {
		this.usuario = usuario;
	}
	public String getException() {
		return erro;
	}
	public void setException(String erro) {
		this.erro = erro;
	}

}
