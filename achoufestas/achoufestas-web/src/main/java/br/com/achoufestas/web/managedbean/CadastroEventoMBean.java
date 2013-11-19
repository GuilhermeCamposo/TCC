package br.com.achoufestas.web.managedbean;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import br.com.achoufestas.ejb.bean.EventoBean;
import br.com.achoufestas.ejb.entidade.Endereco;
import br.com.achoufestas.ejb.entidade.Evento;
import br.com.achoufestas.ejb.entidade.ImagemEvento;
import br.com.achoufestas.ejb.enumeration.ETipoImagem;
import br.com.achoufestas.ejb.expection.DALException;

@ManagedBean
@ViewScoped
public class CadastroEventoMBean extends DefaultMBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8012168516807369935L;
    private static final Logger LOG = Logger.getLogger(CadastroEventoMBean.class.getName());
    
	@EJB
	private EventoBean eventoBean;
	
	private Evento eventoNovo;
    
    private ArrayList<ImagemEvento> files = new ArrayList<ImagemEvento>();
    
    @PostConstruct
    public void init(){
    	setEventoNovo(new Evento());
    	getEventoNovo().setEndereco(new Endereco());
    	getEventoNovo().setDataEvento(new Date());
    }
    
    public void uploadListenerP(FileUploadEvent event){
        UploadedFile item = event.getUploadedFile();
        ImagemEvento file = new ImagemEvento();  
        	file.setFile(item.getData());
        	file.setTipo(ETipoImagem.M);
        files.add(file);
    }
    
    public void uploadListenerG(FileUploadEvent event){
        UploadedFile item = event.getUploadedFile();
        ImagemEvento file = new ImagemEvento();
    		file.setFile(item.getData());
    		file.setTipo(ETipoImagem.N);
    	files.add(file);
    }

	public void cadastrarEvento() {
		try {
			if(this.getUsuarioLogado()!=null){
				eventoNovo.setIdProdutor(this.getUsuarioLogado().getIdUsuario());
		    	getEventoNovo().setEndereco(getEventoNovo().getEndereco());
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