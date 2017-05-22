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
@Table(name="CAT_ESTADO_USUARIO")
public class EstadoUsuario {

	@Id
	@Column(name="ID_ESTADO_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstadoUsuario;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoUsuario")
	private Set<Usuario> usuario = new HashSet<Usuario>(0);
	
	public Integer getIdEstadoUsuario() {
		return idEstadoUsuario;
	}
	public void setIdEstadoUsuario(Integer idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "EstadoUsuario [idEstadoUsuario=" + idEstadoUsuario
				+ ", descripcion=" + descripcion + ", usuario=" + usuario + "]";
	}
}
