package com.lalo.config.dao;

import com.lalo.config.model.Usuario;
import com.lalo.config.springHibernate.HibernateDao;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface UsuarioDao extends  HibernateDao<Usuario, Integer>{

	Usuario findByUserName(String username);

}
