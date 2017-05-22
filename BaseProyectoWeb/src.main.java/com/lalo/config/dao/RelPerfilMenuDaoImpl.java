package com.lalo.config.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lalo.config.model.Perfil;
import com.lalo.config.model.RelPerfilMenu;
import com.lalo.config.model.Usuario;
import com.lalo.config.springHibernate.HibernateDaoImpl;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class RelPerfilMenuDaoImpl extends HibernateDaoImpl<RelPerfilMenu, Integer> implements RelPerfilMenuDao{

	@Override
	public List<RelPerfilMenu> findByPerfil(Perfil perfil) {
		return currentSession().createCriteria(RelPerfilMenu.class, "rpm").
				add(Restrictions.eq("rpm.perfil", perfil)).
				createAlias("rpm.catMenu", "cm").
				add(Restrictions.isNull("cm.menuPadre")).
				addOrder(Order.asc("cm.orden")).
				list();
//		List<RelPerfilMenu> listRelPerfilMenus = (List<RelPerfilMenu>) CollectionUtils.select(
//				currentSession().createCriteria(RelPerfilMenu.class, "rpm").
//				add(Restrictions.eq("rpm.perfil", perfil))
//				.createAlias("rpm.catMenu", "cm").
//				add(Restrictions.isNull("cm.menuPadre")).
//				addOrder(Order.asc("cm.orden")).
//				list(),
//				new MyPredicate("catMenu.menuPadre", null));
	}

	@Override
	public List<RelPerfilMenu> findByUsuario(Usuario usuario) {
		return findByPerfil(usuario.getPerfil());
	}

	@Override
	public List<RelPerfilMenu> findByUsuarioAndMenuPadre(Usuario usuario,RelPerfilMenu relPerfilMenu) {
		return findByPerfilAndMenuPadre(usuario.getPerfil(),relPerfilMenu);
	}
	@Override
	public List<RelPerfilMenu> findByPerfilAndMenuPadre(Perfil perfil,
			RelPerfilMenu relPerfilMenu) {
		return createCriteria()
				.createAlias("catMenu", "cm")
				.add(Restrictions.eq("perfil", perfil))
				.add(Restrictions.eq("cm.menuPadre", relPerfilMenu.getCatMenu()))
				.addOrder(Order.asc("cm.orden")).list();
	}

}
