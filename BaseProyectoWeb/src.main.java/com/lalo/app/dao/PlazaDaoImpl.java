package com.lalo.app.dao;

import org.springframework.stereotype.Repository;

import com.lalo.app.model.Plaza;
import com.lalo.config.springHibernate.HibernateDaoImpl;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Repository
public class PlazaDaoImpl extends HibernateDaoImpl<Plaza, Integer> implements PlazaDao{

	
}
