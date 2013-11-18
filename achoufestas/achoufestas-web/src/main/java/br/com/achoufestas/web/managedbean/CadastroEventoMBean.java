package br.com.achoufestas.web.managedbean;

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
public class CadastroEventoMBean extends DefaultMBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8012168516807369935L;
	@EJB
	private EventoBean eventoBean;
	private Evento eventoNovo;
	
    private static final Logger LOG = Logger.getLogger(CadastroEventoMBean.class.getName());
    
    
    @PostConstruct
    public void init(){
    	setEventoNovo(new Evento());
    }
    

	public void cadastrarEvento() {
		try {
			if(this.getUsuarioLogado()!=null){
				eventoNovo.setIdProdutor(this.getUsuarioLogado().getIdUsuario());
				eventoBean.salvarEvento(eventoNovo);
		    	exibirMensagemSucesso("Cadastro do Evento efetuado com sucesso !");
		    	eventoNovo = new Evento();
			}else{
	    		exibirMensagemAviso("Faça seu login para cadastrar um novo evento.");
			}
			
    	}catch (DALException e) {
			LOG.error("Erro ao salvar o evento", e);
    		exibirMensagemErro("Ocorreu um erro salvar o evento.");
		}
	}


	public Evento getEventoNovo() {
		return eventoNovo;
	}


	public void setEventoNovo(Evento eventoNovo) {
		this.eventoNovo = eventoNovo;
	}

	
}