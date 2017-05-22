package com.lalo.config.dao;

import com.lalo.config.model.ParametroGeneral;
import com.lalo.config.springHibernate.HibernateDao;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public interface ParametroGeneralDao extends HibernateDao<ParametroGeneral, String>{
	
	public ParametroGeneral findByClave(String clave);
	public Integer findByClaveReturnInteger(String clave);
	public boolean findByClaveReturnBoolean(String clave);
}
