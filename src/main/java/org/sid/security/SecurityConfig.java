package org.sid.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource datasource;
	 
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(datasource)
//		.usersByUsernameQuery("select username,password, active from utilisateur WHERE username=?")
//		.authoritiesByUsernameQuery("select username, roles from users_roles where username=?").rolePrefix("ROLE_");
		
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username as principal, "
				+ "password as credentials, active from utilisateur where username=?")
		.authoritiesByUsernameQuery(
				"select username as principal, roles as roles from users_roles where username=?")
		.rolePrefix("ROLE_");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/acceuil","/demandeachat").hasRole("USER");
		http.authorizeRequests().antMatchers("/ajoutservice","/allservices",
				"/ajoutposte","/allpostes","/ajoututilisateur").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/numerisationprestation").hasRole("ED");
		http.authorizeRequests().antMatchers("/historiqueprestation","/historiquedemandeachat","/historiquebonachat","/creationbonlivraison",
				"/historiquebonlivraison").hasRole("SECR");
		http.authorizeRequests().antMatchers("/historiquedemandeappro",
				"/historiquebonachat","/creationboncommande","/historiquebonacommande","/creationbonlivraison",
				"/historiquebonlivraison","/ajoutypeproduit","/allcategories",
				"/ajoutproduit","/allproduits","/creationfichesortie","/historiquefichedesortie",
				"/ajoutfournisseur","/historiquefournisseur").hasRole("GS");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}
