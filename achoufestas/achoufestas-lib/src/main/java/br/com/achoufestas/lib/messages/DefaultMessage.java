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
	public Exception getErro() {
		return erro;
	}
	public void setErro(Exception erro) {
		this.erro = erro;
	}

}
