package com.lalo.config.dao;

import java.util.List;

import com.lalo.config.model.Perfil;
import com.lalo.config.model.RelPerfilMenu;
import com.lalo.config.model.Usuario;
import com.lalo.config.springHibernate.HibernateDao;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface RelPerfilMenuDao extends HibernateDao<RelPerfilMenu, Integer>{
	public List<RelPerfilMenu> findByPerfil(Perfil perfil);
	public List<RelPerfilMenu> findByUsuario(Usuario usuario);
	public List<RelPerfilMenu> findByUsuarioAndMenuPadre(Usuario usuario,RelPerfilMenu relPerfilMenu);
	public List<RelPerfilMenu>  findByPerfilAndMenuPadre(Perfil perfil,RelPerfilMenu relPerfilMenu);
}
