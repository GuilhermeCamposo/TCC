package br.com.achoufestas.webservices.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.achoufestas.ejb.entidade.Evento;
import br.com.achoufestas.lib.entidades.EventoApp;

public class EventoConverter {

	public static EventoApp toApp(Evento eventoEntity){
		EventoApp evento = null;
		
		SimpleDateFormat format = 
	            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if(eventoEntity!=null){
			evento = new EventoApp();
			evento.setIdEvento(eventoEntity.getIdEvento());
			evento.setDataEvento(format.format(eventoEntity.getDataEvento()));
			evento.setFoto("http://www.competiracores.com/img/no-photo.jpg");
			//TODO remover gambi
			evento.setNome(eventoEntity.getNome());
			evento.setDescricao(eventoEntity.getDescricao());
		}
		return evento;
	}
	
	
	public static List<EventoApp> toApp(List<Evento> eventoListEntity){
		List<EventoApp> eventoAppList = null;
		if(eventoListEntity!=null){
			eventoAppList = new ArrayList<EventoApp>();
			for (Evento e : eventoListEntity) {
				eventoAppList.add(toApp(e));
			} 
		}
		return eventoAppList;
	}


}
