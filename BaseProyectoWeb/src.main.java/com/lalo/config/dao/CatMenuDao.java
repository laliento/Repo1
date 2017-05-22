package com.lalo.config.dao;

import org.springframework.stereotype.Repository;

import com.lalo.config.model.CatMenu;
import com.lalo.config.springHibernate.HibernateDao;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Repository
public interface CatMenuDao extends HibernateDao<CatMenu, Integer>{
	
}
