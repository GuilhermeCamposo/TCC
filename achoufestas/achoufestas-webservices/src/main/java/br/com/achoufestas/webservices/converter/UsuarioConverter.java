package br.com.achoufestas.webservices.converter;

import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.lib.entidades.UsuarioApp;

public class UsuarioConverter {

	public static Usuario toDatabase(UsuarioApp app){
		Usuario usuario = new Usuario();
		
		return usuario;
	}
	
	public static UsuarioApp toApp(Usuario usuario){
		UsuarioApp app = new UsuarioApp();
		app.setEmail(usuario.getEmail());
		app.setNome(usuario.getNome());
		app.setIdUsuario(usuario.getIdUsuario());
		
		return app;
	}
	
	
	

}
