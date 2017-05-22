package com.lalo.config.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="PARAMETRO_GENERAL")
public class ParametroGeneral implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CLAVE",length=100,nullable=false)
	private String clave;
	@Column(name="VALOR",length=500,nullable=false)
	private String valor;
	@Column(name="DESCRIPCION",length=100,nullable=true)
	private String descripcion;
	public ParametroGeneral(){}
	public ParametroGeneral(String clave, String valor, String descripcion) {
		super();
		this.clave = clave;
		this.valor = valor;
		this.descripcion = descripcion;
	}
	public ParametroGeneral(String clave, String valor) {
		super();
		this.clave = clave;
		this.valor = valor;
	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "ParametroGeneral [clave=" + clave + ", valor=" + valor + ", descripcion=" + descripcion + "]";
	}
}
