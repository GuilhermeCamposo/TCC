package br.com.achoufestas.lib.messages;

import java.io.Serializable;
import java.util.List;

import br.com.achoufestas.lib.entidades.EventoApp;

public class ListagemEventosMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2672881780798098746L;
	private List<EventoApp> eventos;
	private String erro;

	public List<EventoApp> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoApp> eventos) {
		this.eventos = eventos;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}