package com.lalo.config.dao;

import org.springframework.stereotype.Repository;

import com.lalo.config.model.CatMenu;
import com.lalo.config.springHibernate.HibernateDaoImpl;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Repository
public class CatMenuDaoImpl extends HibernateDaoImpl<CatMenu, Integer> implements CatMenuDao{

}
