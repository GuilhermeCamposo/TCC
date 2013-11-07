package br.com.achoufestas.android.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;

public class AcessoBean {

	// TODO remover mock
	private String senhaMock = "teste", loginMock = "teste";

	public UsuarioApp logar(String login, String senha) {

		if (login.equals(loginMock) && senhaMock.equals(senha)) {
			UsuarioApp user = new UsuarioApp();
			user.setEmail("teste@email.com");
			user.setNome("Teste");
			user.setSenha("Teste");
			return user;
		}

		return null;
	}

	// TODO remover mock
	public List<EventoApp> buscarEvento(UsuarioApp usuario){
		
		List<EventoApp> eventos = new ArrayList<EventoApp>();
		
		EventoApp evento = new EventoApp();
		evento.setNome("Rock n` Roll");
		evento.setLocal("Botafogo");
		
		EventoApp evento1 = new EventoApp();
		evento1.setNome("Baile da Anitta");
		evento1.setLocal("Flamengo");
		
		eventos.add(evento1);
		eventos.add(evento);
		
		return eventos;
	}

}
