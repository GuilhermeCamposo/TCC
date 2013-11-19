package br.com.achoufestas.web.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.achoufestas.ejb.bean.EventoBean;
import br.com.achoufestas.ejb.entidade.Evento;
import br.com.achoufestas.ejb.expection.DALException;

@ManagedBean
@ViewScoped
public class EventoMBean extends DefaultMBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8012168516807369935L;
	
    private static final Logger LOG = Logger.getLogger(EventoMBean.class.getName());

	@EJB
	private EventoBean eventoBean;
	
	
	private List<Evento> eventosList;
	
    
    
    @PostConstruct
    public void init(){

    	
    }
    

	public List<Evento> getEventosList() {
		if(eventosList==null){
			eventosList = findEventosByIdProdutor(this.getUsuarioLogado().getIdUsuario());
		}
		return eventosList;
	}

	public void setEventosList(List<Evento> eventosList) {
		this.eventosList = eventosList;
	}
	
	private List<Evento> findEventosByIdProdutor(Long idProdutor){
		try {
			return eventoBean.getEventoByProdutor(idProdutor);
		} catch (DALException e) {
			LOG.error("Erro ao buscar os eventos", e);
    		exibirMensagemErro("Ocorreu um erro os buscar os eventos.");
		}
		return eventosList;
	}

}