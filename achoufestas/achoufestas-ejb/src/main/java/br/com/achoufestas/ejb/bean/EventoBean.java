package br.com.achoufestas.ejb.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.achoufestas.ejb.entidade.Evento;
import br.com.achoufestas.ejb.expection.DALException;

/**
 * Bean responsável pelo Evento
 * 
 * 
 */
@Stateless
@LocalBean
public class EventoBean  {

	@PersistenceContext(unitName = "achoufestas")
	private EntityManager session;

	public Evento salvarEvento(Evento evento) throws DALException {
		try {
			if (evento.getIdEvento() == null) {
				evento.setDataCadastro(new Date());
				session.persist(evento);
			} else {
				session.merge(evento);
			}
		} catch (Exception e) {
			throw new DALException(e);
		}

		return evento;
	}

	@SuppressWarnings("unchecked")
	public List<Evento> getEventoByUser(Long idUsuario) throws DALException {
		List<Evento> eventoList = null;
		try {
			eventoList = (List<Evento>) session.createQuery("select e from Evento e where usuarios.idUsuario = ?")
						.setParameter(1, idUsuario)
						.getResultList();

		} catch (Exception e) {
			throw new DALException(e);
		}

		return eventoList;

	}

	/**
	 * busca os eventos de maneira geral, posteriormente mudaremos isso para ter
	 * um busca paginada
	 * 
	 * @return
	 * @throws DALException
	 */
	@SuppressWarnings("unchecked")
	public List<Evento> getEventos() throws DALException {
		List<Evento> eventoList =null;
		try {
			eventoList = (List<Evento>) session.createQuery("select e from Evento e order by e.dataEvento, e.nome")
						.getResultList();

		} catch (Exception e) {
			throw new DALException(e);
		}

		return eventoList;

	}

	/**
	 * busca inteligente apartir de uma string passada
	 * 
	 * @return
	 * @throws DALException
	 */
	@SuppressWarnings("unchecked")
	public List<Evento> getEventosPorNome(String nome) throws DALException {
		
		List<Evento> eventoList = null;
		try {
			eventoList = (List<Evento>) session.createQuery("select e from Evento e where nome like ?")
					.setParameter(1,"%"+nome+"%");

		} catch (Exception e) {
			throw new DALException(e);
		}

		return eventoList;


	}

	/**
	 * busca inteligente apartir de uma string passada
	 * 
	 * @return
	 * @throws DALException
	 */
	@SuppressWarnings("unchecked")
	public List<Evento> getEventosPorCoordenada(Double latitude, Double longitude) throws DALException {
		// TODO Implementar busca por coordenada

		List<Evento> eventoList =null;
		try {
			eventoList = (List<Evento>) session.createQuery("select e from Evento e order by e.dataEvento, e.nome")
						.getResultList();

		} catch (Exception e) {
			throw new DALException(e);
		}

		return eventoList;

	}

}
