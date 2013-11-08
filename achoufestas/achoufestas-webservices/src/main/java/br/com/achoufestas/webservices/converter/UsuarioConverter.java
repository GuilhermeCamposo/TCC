package br.com.achoufestas.webservices.converter;

import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.lib.entidades.UsuarioApp;

public class UsuarioConverter {

	public static Usuario toDatabase(UsuarioApp app){
		Usuario usuario = new Usuario();
		usuario.setEmail(app.getEmail());
		usuario.setNome(app.getNome());
		usuario.setSenha(app.getSenha());
		
		return usuario;
	}
	
	public static UsuarioApp toApp(Usuario usuario){
		UsuarioApp app = new UsuarioApp();
		app.setEmail(usuario.getEmail());
		app.setNome(usuario.getNome());
		app.setIdUsuario(usuario.getIdUsuario());
		app.setEventosMarcados(EventoConverter.toApp(usuario.getEventos()));
		return app;
	}
	
	
	

}
