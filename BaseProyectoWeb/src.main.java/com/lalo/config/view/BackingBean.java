package com.lalo.config.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.sessions.PropertiesHandler;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lalo.config.dao.UsuarioDao;
import com.lalo.config.model.Usuario;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
public class BackingBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected RequestContext contextRequest;
	protected FacesContext contextFaces = FacesContext.getCurrentInstance();
	protected Logger log;
	private boolean flagErrorMsg;
	@Autowired
	private UsuarioDao usuarioDao;
	private Usuario usuario;
	private Authentication auth;
	public BackingBean() {
		loadLogger();
		setFlagErrorMsg(false);	
	}
	public Usuario getSessionUser()
	{	
		getPageByUser();
		if(this.usuario == null){
			auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
			return (Usuario) usuarioDao.findByUserName(auth.getName());
		}else
			return usuario;
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public String getPageByUser(){
		String pagina="login.xhtml?faces-redirect=true";
//		if(auth == null) // se comenta para recuperar el role desde la p�gina de login si es que ya estaba logeado se redireccione
		auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()){
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    auth.getAuthorities();
			for (SimpleGrantedAuthority simpleGrantedAuthority : authorities) {//pregunta por rol cual es su p�gina de inicio
				if(simpleGrantedAuthority.toString().equals("ROLE_ADMIN")){
					pagina="/pages/admin/admin.xhtml?faces-redirect=true";
				}
			}
		}else{//regresa a loggin
			pagina = null;
		}
		return pagina;
	}
	public Authentication getAuthentication()
	{
		if(auth == null)
			auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	public void closeDialog(String widgetVarDialog, boolean flagErrorMsg) {
		if (!flagErrorMsg) {
			contextRequest = RequestContext.getCurrentInstance();
			contextRequest.execute(widgetVarDialog + ".hide()");
		}
	}
	public void openDialog(String widgetVarDialog, boolean success) {
		if (success) {
			contextRequest = RequestContext.getCurrentInstance();
			contextRequest.execute(widgetVarDialog + ".show()");
		}
	}
	public void refreshComponent(String componentUpdate) {
		contextRequest = RequestContext.getCurrentInstance();
		contextRequest.update(componentUpdate);
	}
	public void loadLogger() {
		try {
			if(log == null){
				//cargda la configuraci�n desde spring desde el web.xml
				log = Logger.getLogger(this.getClass());
			}
			else{
				log = Logger.getLogger(this.getClass());
			}
			//System.out.println("\n -- Logger Inicializado --");
		} catch (Exception e) {
			BasicConfigurator.configure();
			log = Logger.getLogger(this.getClass());
			System.out.println("Excepcion al inicializar el log "
					+ e.toString());
		}
	}
	public static Properties loadProperties(String propertiesFileName) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = PropertiesHandler.class
					.getResourceAsStream(propertiesFileName);
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;
	}
	public void limpiarMemoria() {
		try {
			log.info("INI GC.");
			Runtime.getRuntime().gc();
			log.info("FIN GC.");
		} catch (Exception e) {
			log.error("Fallo la ejecuci�n del GC. " + e);
		}
	}
	public boolean isFlagErrorMsg() {
		return flagErrorMsg;
	}
	public void setFlagErrorMsg(boolean flagErrorMsg) {
		this.flagErrorMsg = flagErrorMsg;
	}
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
}
