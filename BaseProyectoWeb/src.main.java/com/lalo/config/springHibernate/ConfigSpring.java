package com.lalo.config.springHibernate;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/com/lalo/config/properties/database.properties")
@ComponentScan(basePackages = {
		"com.lalo.app.view","com.lalo.app.bi","com.lalo.app.dao",//referente al tipo de aplicacion
		"com.lalo.config.view","com.lalo.config.dao",//referente a la configuración del sistema
		"com.lalo.config.springSecurity"
		})// por default toma el paquete donde est� ubicada �sta clase
@EnableTransactionManagement
public class ConfigSpring {
	@Autowired
    Environment env;
	@Bean(name = "jpaVendorAdapter")
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter
				.setDatabasePlatform(env.getProperty("hibernate.dialect"));
		return hibernateJpaVendorAdapter;
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	  PropertySourcesPlaceholderConfigurer pspc
	    = new PropertySourcesPlaceholderConfigurer();
	  Resource[] resources = new ClassPathResource[ ]
	    { new ClassPathResource( "/com/lalo/config/properties/general.properties" ) };
	  pspc.setLocations( resources );
	  pspc.setIgnoreUnresolvablePlaceholders( true );
	  return pspc;
	}
	@Bean(name = "DataSource")
	public DriverManagerDataSource DataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(env.getProperty("driverManagerDataSource.DriverClassName"));
		driverManagerDataSource.setUrl(env.getProperty("driverManagerDataSource.Url"));
		driverManagerDataSource.setUsername(env.getProperty("driverManagerDataSource.Username"));
		driverManagerDataSource.setPassword(env.getProperty("driverManagerDataSource.Password"));
		return driverManagerDataSource;
	}

	@Bean(name = "defaulLobHandler")
	public DefaultLobHandler defaulLobHandler() {
		return new DefaultLobHandler();
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(DataSource());
		//para la persistencia de hibernet no de spring Objetos anotados con Hibernate/JPA
		sessionFactory.setPackagesToScan(new String[] {"com.lalo.config.model","com.lalo.app.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean(name="transactionManager")
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory( sessionFactory().getObject());
		return hibernateTransactionManager;
	}
	
	//bean que ayuda al manejo de excepciones para la anotaci�n @Repository Spring pag. 341
	@Bean
	public BeanPostProcessor persistenceTraslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// properties de Hibernate
	@SuppressWarnings("serial")
	private Properties hibernateProperties(){
		return new Properties() {
			{
				setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));//update(pasa los cambios de hibernate a la BD),validate(s�lo valida)
				setProperty("hibernate.generate_statistics",env.getProperty("hibernate.generate_statistics"));
				setProperty("hibernate.cfg.formatSQL",env.getProperty("hibernate.cfg.formatSQL"));
				setProperty("hibernate.cfg.useSqlComments",env.getProperty("hibernate.cfg.useSqlComments"));
				setProperty("hibernate.cfg.batch.fetch.size",env.getProperty("hibernate.cfg.batch.fetch.size"));
				setProperty("hibernate.connection.pool_size",env.getProperty("hibernate.connection.pool_size"));
				setProperty("current_session_context_class",env.getProperty("current_session_context_class"));
				setProperty("hibernate.enable_lazy_load_no_trans",env.getProperty("hibernate.enable_lazy_load_no_trans"));
			}
		};
	}
}