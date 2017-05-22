package com.lalo.app.view;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lalo.app.dao.PlazaDao;
import com.lalo.app.model.Plaza;
import com.lalo.config.view.BackingBean;
import com.lalo.config.view.ViewMethodDefault;

/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
@SessionScoped
@ManagedBean
public class CrudApeView extends BackingBean implements ViewMethodDefault{
	private static final long serialVersionUID = 1L;

	@Autowired
	PlazaDao plazaDao;
	
	List<Plaza> lstPlazas;
	private Plaza plazaTmp;
	private Integer count;
	@PostConstruct
	@Override
	public void initPage() {
		lstPlazas = plazaDao.findAll();
		count = lstPlazas.size();
		initTmp();
	}
	
	public void guardar(){
		plazaDao.saveOrUpdate(plazaTmp);
		FacesMessage msg = new  FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setDetail("Plaza Guardada");
		msg.setSummary("OK");
		contextFaces.addMessage("Ok", msg);
		initPage();
	}
	public void initTmp(){
		plazaTmp = new Plaza();
	}
	public void eliminar(){
		System.out.println("delete:"+plazaTmp);
		plazaDao.delete(plazaTmp);
		initPage();
	}
	
	/* (non-Javadoc)
	 * @see com.lalo.config.view.ViewMethodDefault#irPagina()
	 */
	@Override
	public String irPagina() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lalo.config.view.ViewMethodDefault#loadElements()
	 */
	@Override
	public void loadElements() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.lalo.config.view.ViewMethodDefault#cleanPage()
	 */
	@Override
	public void cleanPage() {
		// TODO Auto-generated method stub
		
	}
	
	public Plaza getPlazaTmp() {
		return plazaTmp;
	}


	public void setPlazaTmp(Plaza plazaTmp) {
		this.plazaTmp = plazaTmp;
	}


	public List<Plaza> getLstPlazas() {
		return lstPlazas;
	}
	public void setLstPlazas(List<Plaza> lstPlazas) {
		this.lstPlazas = lstPlazas;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
