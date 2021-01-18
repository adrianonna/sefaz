package br.gov.pe.sefaz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_telefone")
public class Telefone implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
//	@Column(name="nu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nu_ddd")
	private Integer ddd;
	
	@Column(name="nm_numero")
	private String numero;
	
	@Column(name="nm_tipo")
	private String tipo;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="usuario_id") //, referencedColumnName = "id"
	private Usuario usuario;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Telefone [ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + "]";
	}

}
