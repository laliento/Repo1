package com.lalo.config.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Entity
@Table(name="CAT_MENU")
public class CatMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_MENU")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenu;
	@Column(name="DESC",length = 50)
	private String desc;
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ID_MENU_PADRE",nullable=true)
	private CatMenu menuPadre;
	@Column(name="URL",length=100,nullable=true)
	private String url;
	@Column	(name="ORDEN")
	private Integer orden;
	@Column(name="ICONO",length=50)
	private String icono;
	@OneToMany(mappedBy="menuPadre")
	private Set<CatMenu> subMenus = new HashSet<CatMenu>();
	public CatMenu() {}
	public CatMenu(String desc, CatMenu menuPadre, String url, Integer orden,
			String icono) {
		super();
		this.desc = desc;
		this.menuPadre = menuPadre;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
	}

	public CatMenu(String desc, CatMenu menuPadre, String url, Integer orden,
			String icono, Set<CatMenu> subMenus) {
		super();
		this.desc = desc;
		this.menuPadre = menuPadre;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
		this.subMenus = subMenus;
	}

	public CatMenu(Integer idMenu) {
		super();
		this.idMenu = idMenu;
	}

	public CatMenu(String desc, String url, Integer orden, String icono) {
		this.desc = desc;
		this.url = url;
		this.orden = orden;
		this.icono = icono;
	}
	public Integer getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public CatMenu getMenuPadre() {
		return menuPadre;
	}
	public void setMenuPadre(CatMenu menuPadre) {
		this.menuPadre = menuPadre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public Set<CatMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(Set<CatMenu> subMenus) {
		this.subMenus = subMenus;
	}
	@Override
	public String toString() {
		return "CatMenu [idMenu=" + idMenu + ", desc=" + desc + ", menuPadre="
				+ menuPadre + ", url=" + url + ", orden=" + orden + ", icono="
				+ icono + ", subMenus=" + subMenus + "]";
	}
}