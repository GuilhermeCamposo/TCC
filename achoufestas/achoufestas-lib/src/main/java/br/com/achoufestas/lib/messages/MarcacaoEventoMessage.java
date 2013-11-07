package br.com.achoufestas.lib.messages;

import java.io.Serializable;

public class MarcacaoEventoMessage implements Serializable {

	private static final long serialVersionUID = -7284163119289829419L;
	
	private long idUsuario;
	private long idEvento;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}
	


}
