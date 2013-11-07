package br.com.achoufestas.lib.messages;

import java.io.Serializable;
import java.util.List;

import br.com.achoufestas.lib.entidades.EventoApp;

public class LoginMessage extends DefaultMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8914090509183768284L;
	private List<EventoApp> eventos;

	public List<EventoApp> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoApp> eventos) {
		this.eventos = eventos;
	}

}
