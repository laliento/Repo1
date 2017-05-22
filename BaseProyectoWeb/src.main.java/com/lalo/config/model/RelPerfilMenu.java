package com.lalo.config.model;

import java.io.Serializable;

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
@Table(name="REL_PERFIL_MENU")
public class RelPerfilMenu implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REL_PERFIL_MENU")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRelPerfilMenu;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private Perfil perfil;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENU", nullable = false)
	private CatMenu  catMenu;
	public RelPerfilMenu() {}
	public RelPerfilMenu(Integer idRelPerfilMenu) {
		this.idRelPerfilMenu = idRelPerfilMenu;
	}
	public RelPerfilMenu(Perfil perfil, CatMenu catMenu) {
		this.perfil = perfil;
		this.catMenu = catMenu;
	}
	public Integer getIdRelPerfilMenu() {
		return idRelPerfilMenu;
	}
	public void setIdRelPerfilMenu(Integer idRelPerfilMenu) {
		this.idRelPerfilMenu = idRelPerfilMenu;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public CatMenu getCatMenu() {
		return catMenu;
	}
	public void setCatMenu(CatMenu catMenu) {
		this.catMenu = catMenu;
	}
	@Override
	public String toString() {
		return "RelPerfilMenu [idRelPerfilMenu=" + idRelPerfilMenu
				+ ", perfil=" + perfil + ", catMenu=" + catMenu + "]";
	}
}
