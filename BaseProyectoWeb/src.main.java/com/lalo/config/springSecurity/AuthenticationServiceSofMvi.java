package com.lalo.config.springSecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lalo.config.dao.LogginAttemptsDao;
import com.lalo.config.dao.ParametroGeneralDao;
import com.lalo.config.dao.UsuarioDao;
import com.lalo.config.model.Usuario;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Component
public class AuthenticationServiceSofMvi implements UserDetailsService{

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private LogginAttemptsDao logginAttemptsDao;
	@Autowired
	private ParametroGeneralDao parametroGeneralDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUserName(username);
		//si no existe el usuario se lanza InternalAuthenticationServiceException que es cachada
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+usuario.getPerfil().getDescripcion());
		Boolean enabled = usuario.isEnable();
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired = true;
		Boolean accountNonLocked = !evitaFuerzaBruta(usuario);
		User user = new User(usuario.getUsername(), 
				usuario.getPassword()+"|"+usuario.getSalt(), 
				enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,
				Arrays.asList(authority));
		UserDetails userDetails = (UserDetails)user;
		return userDetails;
	}
	private Boolean evitaFuerzaBruta(Usuario usuario) {
		Integer minutosCuentaBloqueada = parametroGeneralDao.findByClaveReturnInteger("minutosCuentaBloqueada");
		Integer intentosPermitidosLoggin = parametroGeneralDao.findByClaveReturnInteger("intentosPermitidosLoggin");
		Integer numeroDeIntentos = logginAttemptsDao.findNumeroIntentosByUserAndMinutos(usuario,minutosCuentaBloqueada); 
			if(superaNumeroIntentos(numeroDeIntentos,intentosPermitidosLoggin))
				return true;
		return false;
	}
	private boolean superaNumeroIntentos(Integer numeroDeIntentos,Integer intentosPermitidosLoggin) {
		return numeroDeIntentos>=intentosPermitidosLoggin;
	}
	
}
