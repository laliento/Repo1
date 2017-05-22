package com.lalo.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="ENT_USUARIO")
public class Usuario {
	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	@Column(name="USERNAME")
	private String username;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	@Column(name="CORREO")
	private String correo;
	@Column(name="PASSWORD",columnDefinition="char(128)")
	private String password;
	@Column(name="SALT",columnDefinition="char(128)")
	private String salt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO_USUARIO", nullable = false)
	private EstadoUsuario estadoUsuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private Perfil perfil;
	@Column(name="ENABLE",columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enable;
	public Usuario() {}
	public Usuario(Integer idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
	public Usuario(String username, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
			String password, String salt, EstadoUsuario estadoUsuario, Perfil perfil, boolean enable) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correo = correo;
		this.password = password;
		this.salt = salt;
		this.estadoUsuario = estadoUsuario;
		this.perfil = perfil;
		this.enable = enable;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", username=" + username + ", nombre=" + nombre
				+ ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo
				+ ", password=" + password + ", salt=" + salt + ", estadoUsuario=" + estadoUsuario + ", perfil="
				+ perfil + ", enable=" + enable + "]";
	}
}
