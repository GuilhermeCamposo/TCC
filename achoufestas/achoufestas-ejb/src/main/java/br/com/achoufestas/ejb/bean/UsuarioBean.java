package br.com.achoufestas.ejb.bean;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.achoufestas.ejb.entidade.Evento;
import br.com.achoufestas.ejb.entidade.Usuario;
import br.com.achoufestas.ejb.expection.DALException;

/**
 * Bean responsável pelo Usuario
 * 
 * 
 */
@Stateless
@LocalBean
public class UsuarioBean {

	@PersistenceContext(unitName = "achoufestas")
	private EntityManager session;

	private static final Logger LOG = Logger.getLogger(UsuarioBean.class
			.getName());

	public boolean associarUsuarioEvento(Long idEvento, Long idUsuario) {

		try {
			Usuario user = session.find(Usuario.class, idUsuario);
			user.getEventos().add(new Evento(idEvento));
			this.salvarUsuario(user);
		} catch (Exception e) {
			LOG.error("Erro ao associar um usuário ao evento", e);
			return false;
		}

		return false;
	}

	public boolean desassociarUsuarioEvento(Long idEvento, Long idUsuario) {

		session.createNativeQuery("delete from usuario_evento where id_usuario = :usuario and id_evento= :evento ")
				.setParameter("usuario", idUsuario)
				.setParameter("evento", idEvento).executeUpdate();
		
		return false;
	}

	/**
	 * Deve trazer os eventos em que o usuário foi marcado
	 * 
	 * @param email
	 * @param senha
	 * @return
	 * @throws DALException
	 */
	public Usuario logarUsuario(String email, String senha) throws DALException {
		StringBuilder sb = new StringBuilder("SELECT u ").append(
				"FROM Usuario u ").append("WHERE u.email=? and senha=?");
		try {
			Query q = session.createQuery(sb.toString());
			q.setParameter(1, email).setParameter(2, senha);

			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			LOG.error("Erro ao logar um usuário", e);
			throw new DALException(e);
		}
	}

	public Usuario salvarUsuario(Usuario usuario) throws DALException {
		try {
			if (usuario.getIdUsuario() == null) {
				usuario.setDataCadastro(new Date());
				session.persist(usuario);
			} else {
				session.merge(usuario);
			}

		} catch (Exception e) {
			LOG.error("Erro ao salvar um usuário", e);
			throw new DALException(e);
		}

		return usuario;
	}

}