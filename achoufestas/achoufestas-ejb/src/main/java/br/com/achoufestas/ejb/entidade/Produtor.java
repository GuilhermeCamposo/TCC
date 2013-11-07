package br.com.achoufestas.ejb.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="produtor")
public class Produtor implements Serializable {
	
	private static final long serialVersionUID = 9110701536802472963L;


	@Id
	@Column(name="id_usuario", nullable=false, length=8)
	@GeneratedValue(generator="foreign")     
    @GenericGenerator(name="foreign", strategy="foreign",
    		parameters={@Parameter(name="property", value="usuario")})
	private Long idUsuario;
	
	
	@JoinColumn(name="id_usuario", nullable=false, referencedColumnName="id_usuario", insertable=false, updatable=false)
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	private Usuario usuario;
	
	@Column(length= 11)
	private String cpf;
	
	@Column(length= 14)
	private String cnpj;

	@OneToMany(cascade = CascadeType.ALL , mappedBy="produtor", fetch = FetchType.LAZY)
	private Set<Evento> eventos;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Produtor other = (Produtor) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	
}