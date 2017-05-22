package com.lalo.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table (name="LOGGIN_ATTEMPTS")
public class LogginAttempts implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LOGG_ATT")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idLoggAtt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ID_USUARIO",nullable=false)
	private Usuario usuario;
	@Column(name="TIEMPO")
	private Date tiempo;
	public LogginAttempts(Usuario usuario, Date tiempo) {
		this.usuario = usuario;
		this.tiempo = tiempo;
	}
	public LogginAttempts(Integer idLoggAtt) {
		this.idLoggAtt = idLoggAtt;
	}
	public Integer getIdLoggAtt() {
		return idLoggAtt;
	}
	public void setIdLoggAtt(Integer idLoggAtt) {
		this.idLoggAtt = idLoggAtt;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getTiempo() {
		return tiempo;
	}
	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LogginAttempts [idLoggAtt=" + idLoggAtt + ", usuario=" + usuario + ", tiempo=" + tiempo + "]";
	}
}
