package br.com.achoufestas.web.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.ejb.enumeration.SessionKeys;

public class DefaultMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8396872446001239419L;
	private Usuario usuarioLogado;
	
	protected HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	protected Object getSessionObject(SessionKeys session) {
		return getSession().getAttribute(session.getChave());
	}

	protected void setSessionObject(SessionKeys session, Object objeto) {
		 getSession().setAttribute(session.getChave() ,objeto);
	}	
	
	protected void exibirMensagemSucesso(String mensagem){
		exibirMensagem(FacesMessage.SEVERITY_INFO,mensagem,"Sucesso");
	}
	
	protected void exibirMensagemAviso(String mensagem){
		exibirMensagem(FacesMessage.SEVERITY_WARN,mensagem,"Aviso");
	}
	
	protected void exibirMensagemErro(String mensagem){
		exibirMensagem(FacesMessage.SEVERITY_ERROR,mensagem,"Erro");
	}
	
	protected void exibirMensagem(FacesMessage.Severity tipo, String mensagem, String details){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, mensagem,details));
	}

	public Usuario getUsuarioLogado() {
		if(usuarioLogado==null){
			usuarioLogado = (Usuario) getSessionObject(SessionKeys.USUARIO);
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
