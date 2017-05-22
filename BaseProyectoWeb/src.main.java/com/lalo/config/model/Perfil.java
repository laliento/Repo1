package com.lalo.config.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="CAT_PERFIL")
public class Perfil {

	@Id
	@Column(name="ID_PERFIL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerfil;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private Set<Usuario> usuario = new HashSet<Usuario>(0);
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Usuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(Set<Usuario> usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", descripcion=" + descripcion
				+ ", usuario=" + usuario + "]";
	}
	
}
