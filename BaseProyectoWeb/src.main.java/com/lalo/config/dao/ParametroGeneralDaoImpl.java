package com.lalo.config.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lalo.config.model.ParametroGeneral;
import com.lalo.config.springHibernate.HibernateDaoImpl;
import com.lalo.config.util.StringUtilSoftMvi;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Repository
public class ParametroGeneralDaoImpl extends HibernateDaoImpl<ParametroGeneral, String> implements ParametroGeneralDao{

	@Override
	public ParametroGeneral findByClave(String clave) {
		return (ParametroGeneral) createCriteria().add(Restrictions.eq("clave", clave)).uniqueResult();
	}

	@Override
	public Integer findByClaveReturnInteger(String clave) {
		ParametroGeneral parametroGeneralTmp = findByClave(clave);
		if(parametroGeneralTmp != null)
			if(StringUtilSoftMvi.isNumeric(parametroGeneralTmp.getValor()))
				return Integer.parseInt(parametroGeneralTmp.getValor());
		return null;
	}

	@Override
	public boolean findByClaveReturnBoolean(String clave) {
		ParametroGeneral parametroGeneralTmp = findByClave(clave);
		if(parametroGeneralTmp != null)
				return Boolean.valueOf(parametroGeneralTmp.getValor());
		return false;
	}

}
