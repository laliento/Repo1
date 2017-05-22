package com.lalo.config.dao;

import com.lalo.config.model.LogginAttempts;
import com.lalo.config.model.Usuario;
import com.lalo.config.springHibernate.HibernateDao;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface LogginAttemptsDao extends HibernateDao<LogginAttempts, Integer>{
	
	public Integer findNumeroIntentosByUserAndMinutos(Usuario usuario,Integer minutos);
}
