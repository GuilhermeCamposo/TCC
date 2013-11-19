package br.com.achoufestas.ejb.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.achoufestas.ejb.enumeration.ETipoImagem;

@Entity
@Table(name="imagem_evento")
public class ImagemEvento implements Serializable {
	
	private static final long serialVersionUID = -6580966581408596942L;

	@Id
    @Column(name="id_imagem_evento", nullable=false, length=8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImagemEvento;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_evento")
	private Evento evento;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name="tipo")
	private ETipoImagem tipo;
	
	@JoinColumn(name="url")
	private String url;
	
	@Transient
	private byte[] file;
	
	public Long getIdImagemEvento() {
		return idImagemEvento;
	}

	public void setIdImagemEvento(Long idImagemEvento) {
		this.idImagemEvento = idImagemEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public ETipoImagem getTipo() {
		return tipo;
	}

	public void setTipo(ETipoImagem tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] bs) {
		this.file = bs;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idImagemEvento == null) ? 0 : idImagemEvento.hashCode());
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
		ImagemEvento other = (ImagemEvento) obj;
		if (idImagemEvento == null) {
			if (other.idImagemEvento != null)
				return false;
		} else if (!idImagemEvento.equals(other.idImagemEvento))
			return false;
		return true;
	}
}