package com.lalo.config.springHibernate;

import java.util.Random;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.BaseDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.lalo.config.springSecurity.AuthenticationServiceSofMvi;
import com.lalo.config.springSecurity.UserNameCachingAuthenticationFailureHandlerSoftMvi;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource  datasource;
	@Autowired
	private AuthenticationServiceSofMvi authenticationServiceSofMvi;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(authenticationServiceSofMvi)
		.passwordEncoder(
			new BaseDigestPasswordEncoder() {
				ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
				//cuando llega el pwdUs del suario ya viene con un primer hash512 por js
				@Override
				public boolean isPasswordValid(String pwdBd, String pwdUs, Object arg2) {
					shaPasswordEncoder.setEncodeHashAsBase64(false);
					if(pwdUs.length() != 128 || pwdBd.length() != 257)
						return false;
					String[] pswdBdAndSalt = pwdBd.split(Pattern.quote("|"));
					if(pswdBdAndSalt.length != 2)
						return false;
					if(pswdBdAndSalt[0].length() != 128 || pswdBdAndSalt[1].length() != 128)
						return false;
					return shaPasswordEncoder.isPasswordValid(pswdBdAndSalt[0], pwdUs,pswdBdAndSalt[1]);
				}
				@Override
				public String encodePassword(String paswdUser, Object salt) {
					shaPasswordEncoder.setEncodeHashAsBase64(false);
					return shaPasswordEncoder.encodePassword(paswdUser, salt);
				}
			}
		);
	}
	@Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**","/"); // #No haga caso de cualquier solicitud que comienza con
	  }
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http
//      	.csrf()//todo va con https excepto
//	      .disable()
      .authorizeRequests()
        .antMatchers("/", "/home","/resources/**").authenticated()
      	//internos una vez autenticado
      	.antMatchers("/css/**","/images/**", "/js/**").authenticated()
      	//publicos
      	.antMatchers("/login.xhtml","/vendor/**","/app/**", "/image/**").permitAll()
        .antMatchers("/pages/admin/**").hasRole("ADMIN") //hasRole a�ade el prefijo _ROLE
        .antMatchers("/pages/caja/**").access("hasRole('ADMIN') OR hasRole('CAJA')")
        .antMatchers("/pages/mesero/**").access("hasRole('ADMIN') OR hasRole('MESERO')")
        .antMatchers("/pages/lalo/**").access("isAuthenticated() and principal.username=='admonlalo'")
        .antMatchers("/pages/**").authenticated()
        .and().formLogin()  
	        .loginPage("/login.xhtml")
	        .loginProcessingUrl("/appLogin")
	        .usernameParameter("app_username")//nombre en formulario
            .passwordParameter("app_password")//nombre en formulario
            .defaultSuccessUrl("/login.xhtml")
	        .permitAll()
	        .failureHandler( simpleUrlAuthenticationFailureHandler())
	     .and().logout()
			.logoutUrl("/appLogout") 
			.logoutSuccessUrl("/login.xhtml")
			.deleteCookies("JSESSIONID","remember_me_end")
	     .and().httpBasic()// forma de autententicar a un usuario en una apicaci�n directamente / se puede hacer autom�ticamente con REST
//	     	.realmName("") // nombre del reino ...
	     .and().rememberMe() // usa tokenRepository � key
	     	.rememberMeParameter("remember_me")//nombre en formulario
	     	.rememberMeCookieName("remember_me_end")
	     	.tokenRepository(persistentTokenRepository())//va a la tabla persistent_logins http://websystique.com/spring-security/spring-security-4-remember-me-example-with-hibernate/
	     	.tokenValiditySeconds(60)//numero de segundos que est� activa la session, por defaul son 2 semanas
	     	.key("lalum@")//llave con la que se codifica la cookie, por defaul SpringSecured
	     .and().requiresChannel()
	     	.antMatchers("/").requiresSecure()//requiere https en esta parte
	     .and().exceptionHandling().accessDeniedPage("/accesDenied.xhtml")
//	     .and().authorizeRequests().anyRequest().permitAll()
	     .and().sessionManagement()
//	     	always – una sesión siempre se creará si no existe ya
//		     ifRequired – una sesión sólo se creará si es necesario ( por defecto )
//		     never – el marco nunca crear una sesión en sí, sino que utilizará uno si ya existe
//		     stateless –no hay ninguna sesión será creado o utilizado por la primavera de Seguridad
//	     	.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//	     	.maximumSessions(1)
//	     	.and().sessionFixation().migrateSession()
	     ;
    }
	@Bean
	public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler(){
		return new UserNameCachingAuthenticationFailureHandlerSoftMvi();
	}
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(datasource);
        return tokenRepositoryImpl;
    }
	public static void main(String[] args) {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(false);
		Random r = new Random();
		Integer low = 1000;
		Integer high = 9000;
		Integer result = r.nextInt(high-low) + low;
		//contraseña ingresada por usuario
		String pass="user@Ape";//r.nextInt(high-low) + low;
		String saltRandom = result.toString();
		String saltSha = shaPasswordEncoder.encodePassword(saltRandom, "lalum@");
		//SHA512 que genera js cuando envia
		String primerSha= shaPasswordEncoder.encodePassword(pass, null);
		//SHA512 de primer SHA512 con salt
		String passBD = shaPasswordEncoder.encodePassword(primerSha, saltSha);
		System.out.println("Pwd Usuario:"+pass);
		System.out.println("Salt BD:"+saltSha);
		System.out.println("Pswd BD:"+passBD);
	}
}
