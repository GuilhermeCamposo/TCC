package br.com.achoufestas.ejb.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="evento")
public class Evento implements Serializable {
	
	private static final long serialVersionUID = -6580966581408596942L;

	@Id
    @Column(name="id_evento", nullable=false, length=8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;
	
	@Column(name = "nome", length=120, nullable= false)
	private String nome;
	
	@Column(name = "descricao", length=250)
	private String descricao;

	@Column(name = "data_cadastro", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "data_evento", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEvento;
	
	@Column(name = "id_produtor", nullable= false)
	private Long idProdutor;
	
	@ManyToOne
	@JoinColumn(name="id_produtor", referencedColumnName="id_usuario", insertable=false, updatable=false, nullable=false)
	private Produtor produtor;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy= "eventos")
	private Set<Usuario> usuarios;
	
	public Evento() {
		
	}
	

	public Evento(Long idEvento) {
		super();
		this.idEvento = idEvento;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public Date getDataEvento() {
		return dataEvento;
	}


	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}


	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Long getIdProdutor() {
		return idProdutor;
	}

	public void setIdProdutor(Long idProdutor) {
		this.idProdutor = idProdutor;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEvento == null) ? 0 : idEvento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		return true;
	}

}