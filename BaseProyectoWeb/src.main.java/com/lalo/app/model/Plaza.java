package com.lalo.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name ="ENT_PLAZA")
public class Plaza {

	@Id
	@Column(name = "ID_PLAZA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPlaza;
	@Column(name ="NOMBRE", length=100)
	private String nombre;
	@Column(name="DIRECCION", length=500)
	private String direccion;
	@Column(name="NUMERO_LOCALES")
	private Integer numeroLocales;
	@Column(name="LOCALES_ACTIVOS")
	private Integer localesActivos;
	@Column (name="OBSERVACIONES", length=200)
	private String observaciones;
	public Plaza(){}
	public Plaza(Integer idPlaza) {
		super();
		this.idPlaza = idPlaza;
	}


	public Integer getIdPlaza() {
		return idPlaza;
	}
	public void setIdPlaza(Integer idPlaza) {
		this.idPlaza = idPlaza;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getNumeroLocales() {
		return numeroLocales;
	}
	public void setNumeroLocales(Integer numeroLocales) {
		this.numeroLocales = numeroLocales;
	}
	public Integer getLocalesActivos() {
		return localesActivos;
	}
	public void setLocalesActivos(Integer localesActivos) {
		this.localesActivos = localesActivos;
	}	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		return "Plaza [idPlaza=" + idPlaza + ", nombre=" + nombre + ", direccion=" + direccion + ", numeroLocales="
				+ numeroLocales + ", localesActivos=" + localesActivos + ", precioLocal=" + observaciones + "]";
	}
}
