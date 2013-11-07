package br.com.achoufestas.webservices.service;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.achoufestas.ejb.bean.EventoBean;
import br.com.achoufestas.ejb.bean.UsuarioBean;
import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.ejb.expection.DALException;
import br.com.achoufestas.lib.entidades.Coordenada;
import br.com.achoufestas.lib.entidades.Resposta;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.DefaultMessage;
import br.com.achoufestas.lib.messages.ListagemEventosMessage;
import br.com.achoufestas.lib.messages.LoginMessage;
import br.com.achoufestas.lib.messages.MarcacaoEventoMessage;
import br.com.achoufestas.webservices.converter.EventoConverter;
import br.com.achoufestas.webservices.converter.UsuarioConverter;

@Path("/achouFestaResource")
public class AchouFestasResource {

	private static final Logger LOG = Logger
			.getLogger(AchouFestasResource.class.getName());
	@EJB
	private EventoBean eventoBean;
	@EJB
	private UsuarioBean usuarioBean;

	@POST
	@Path("/busca_coordenada")
	@Produces("application/json")
	@Consumes("application/json")
	public ListagemEventosMessage buscaPorCoordenada(Coordenada coordenada) {

		LOG.info("Recebendo requisição de listagem de eventos por coordenada");

		ListagemEventosMessage eventos = new ListagemEventosMessage();

		try {
			eventos.setEventos(EventoConverter.toApp(eventoBean
					.getEventosPorCoordenada(coordenada.getLatitude(),
							coordenada.getLongitude())));
		} catch (DALException e) {
			e.printStackTrace();
		}

		return eventos;

	}

	@POST
	@Path("/busca_nome")
	@Produces("application/json")
	@Consumes("application/json")
	public ListagemEventosMessage buscaPorNome(String nome) {

		ListagemEventosMessage message = new ListagemEventosMessage();
		try {
			message.setEventos(EventoConverter.toApp(eventoBean
					.getEventosPorNome(nome)));
		} catch (DALException e) {
			e.printStackTrace();
		}

		return message;
	}

	@GET
	@POST
	@Path("/test")
	public Response getTest() {

		return Response.status(200).entity("test").build();
	}

	@POST
	@Path("/login")
	@Produces("application/json")
	@Consumes("application/json")
	public LoginMessage login(UsuarioApp user) {

		LOG.info("Recebendo requisição de login");

		LoginMessage message = new LoginMessage();

		try {
			Usuario usuario = usuarioBean.logarUsuario(user.getEmail(),
					user.getSenha());

			if (usuario != null) {
				message.setUsuario(UsuarioConverter.toApp(usuario));
				message.setEventos(EventoConverter.toApp(eventoBean
						.getEventos()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setErro(e);
		}

		return message;

	}

	@POST
	@Path("/cadastrar")
	@Produces("application/json")
	@Consumes("application/json")
	public DefaultMessage cadastrar(UsuarioApp user) {

		LOG.info("Recebendo requisição de login");

		DefaultMessage message = new DefaultMessage();

		try {
			usuarioBean.salvarUsuario(UsuarioConverter.toDatabase(user));

		} catch (DALException e) {
			message.setErro(new Exception());
		}

		return message;

	}

	@POST
	@Path("/marcar_evento")
	@Consumes("application/json")
	public Resposta marcarEvento(MarcacaoEventoMessage message) {
		LOG.info("Recebeu pedido de marcação");

		usuarioBean.associarUsuarioEvento(message.getIdEvento(),
				message.getIdUsuario());

		return Resposta.VALID;
	}

	@POST
	@Path("/desmarcar_evento")
	@Consumes("application/json")
	public Resposta desmarcarEvento(MarcacaoEventoMessage message) {
		LOG.info("Recebeu pedido de marcação");

		usuarioBean.desassociarUsuarioEvento(message.getIdEvento(),
				message.getIdUsuario());

		return Resposta.VALID;
	}

}
