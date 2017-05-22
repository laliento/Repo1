package com.lalo.config.springHibernate;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
/*
 �sta clase es lo equivalente en el web.xml a
 <filter>
  <filter-name>springSecurityFilterChain</filter-name>
  <filter-class>
    org.springframework.web.filter.DelegatingFilterProxy
  </filter-class>
</filter>

<filter-mapping>
  <filter-name>springSecurityFilterChain</filter-name>
  <url-pattern>/*</url-pattern>
  <dispatcher>ERROR</dispatcher>
  <dispatcher>REQUEST</dispatcher>
</filter-mapping>
	*/
}
