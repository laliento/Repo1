package com.lalo.config.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lalo.config.dao.RelPerfilMenuDao;
import com.lalo.config.model.CatMenu;
import com.lalo.config.model.RelPerfilMenu;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
@SessionScoped
@ManagedBean(name="loginView",eager=true)
public class LoginView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;
	@Autowired
	private RelPerfilMenuDao relPerfilMenuDao;
	private List<RelPerfilMenu> lstRelPerfilMenu;
	@Override
	public String irPagina() {
		String pagina =getPageByUser(); 
		if (pagina.contains("login"))
			return null;
		else{
			lstRelPerfilMenu=relPerfilMenuDao.findByUsuario(getSessionUser());
			for (RelPerfilMenu relPerfilMenu : lstRelPerfilMenu) {
				Set<CatMenu> subMenus = new HashSet<CatMenu>();
				for (RelPerfilMenu relPerfilMenuSub : relPerfilMenuDao.findByUsuarioAndMenuPadre(getSessionUser(),relPerfilMenu)) {
					subMenus.add(relPerfilMenuSub.getCatMenu());
				}
				relPerfilMenu.getCatMenu().setSubMenus(subMenus);
			}
			return pagina;
		}
	}
	@Override
	public void initPage() {}
	@Override
	public void loadElements() {}
	@Override
	public void cleanPage() {}
	public List<RelPerfilMenu> getLstRelPerfilMenu() {
		return lstRelPerfilMenu;
	}
	public void setLstRelPerfilMenu(List<RelPerfilMenu> lstRelPerfilMenu) {
		this.lstRelPerfilMenu = lstRelPerfilMenu;
	}
}
