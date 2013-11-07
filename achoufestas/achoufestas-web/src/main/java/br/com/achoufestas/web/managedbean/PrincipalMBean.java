package br.com.achoufestas.web.managedbean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.achoufestas.ejb.bean.UsuarioBean;
import br.com.achoufestas.ejb.entidade.Produtor;
import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.ejb.enumeration.EStatus;
import br.com.achoufestas.ejb.enumeration.SessionKeys;
import br.com.achoufestas.ejb.expection.DALException;

@ManagedBean
@ViewScoped
public class PrincipalMBean extends DefaultMBean{

    private static final long serialVersionUID = 8301865434469950945L;

    private String email, senha;
    private Usuario novoUsuario;
    
    private static final Logger LOG = Logger.getLogger(PrincipalMBean.class.getName());
    
    @EJB
    private UsuarioBean  usuarioBean;

    @PostConstruct
    public void init(){
    	novoUsuario = new Usuario();
    	novoUsuario.setProdutor(new Produtor());
    }
    
    public void cadastrarProdutor(){
    	try {
    		
    		novoUsuario.setStatus(EStatus.ATIVO.getCodigo());
    		novoUsuario.getProdutor().setUsuario(novoUsuario);
			usuarioBean.salvarUsuario(novoUsuario);
	    	exibirMensagemSucesso("Cadastro efetuado com sucesso !");
	    	this.clearCadastroForm();
		} catch (Exception e) {
			LOG.error("Erro ao realizar o cadastro do produtor", e);
			exibirMensagemErro("Erro ao efetuar o cadastro!");
		}
    	
    }
    
    private void clearCadastroForm(){
    	novoUsuario = new Usuario();
    	novoUsuario.setProdutor(new Produtor());
    }

    public String logarUsuario(){
    	Usuario usuario;
    	try {
    		usuario =	usuarioBean.logarUsuario(email, senha);
    		
		    if(usuario == null){
		    	exibirMensagemAviso("Usuário inválido !");
		    	
		    }else{
		    	setSessionObject(SessionKeys.USUARIO, usuario);
		    	exibirMensagemSucesso("Login efetuado com sucesso !");
		    	return "principal";
		    }
    	}catch (DALException e) {
			LOG.error("Erro ao logar o usuário", e);
    		exibirMensagemErro("Ocorreu um erro ao logar o usuário.");
		}
    	
    	return null;
    }
    
    public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
    
}