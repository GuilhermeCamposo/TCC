package br.com.achoufestas.lib.messages;

import java.io.Serializable;

import br.com.achoufestas.lib.entidades.UsuarioApp;

public class DefaultMessage implements Serializable {

	private Exception erro;
	private UsuarioApp usuario;
	
	public UsuarioApp getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioApp usuario) {
		this.usuario = usuario;
	}
	public Exception getException() {
		return erro;
	}
	public void setException(Exception erro) {
		this.erro = erro;
	}

}
