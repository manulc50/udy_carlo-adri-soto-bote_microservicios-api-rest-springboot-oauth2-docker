package com.paymentchain.billing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@EnableWebSecurity // No hace añadir esta anotación ya que se incluye en la clase de autoconfiguración de Spring Security "SecurityAutoConfiguration"
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Lista de rutas que requieren autenticación
	private static final String[] AUTH_LIST = {
			"/swagger-ui/index.html",
			"/v2/api-docs",
			"/invoice/**"
	};

	// Authentication method(Configura la autenticación)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password(passwordEncoder().encode("password"))
			.roles("USER")
			.and()
			.withUser("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("USER", "ADMIN");
	}
	
	
	// Authorization method(Configura la autorización)
	// Por defecto, la anotación "@EnableWebSecurity" o la clase de autoconfiguración de Spring Security "SecurityAutoConfiguration" habilitan la seguridad CSRF(token CSRF)
	// En una API Rest, como es este proyecto o aplicación, no tiene sentido tener habilitado la seguridad CSRF porque no se trata de una aplicación MVC(Model-View-Controller) con vistas y, por lo tanto, no hay formaularios que permitan modificar recursos
	// Tener habilitado la seguridad CSRF en una API Rest, provoca el error de tipo 403(Forbidden) para cualquier tipo de petición http que modifica algún recurso(POST,PUT o PATCH, DELETE)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Desactivamos la seguridad CSRF ya que estamos en una aplicación API Rest
			.authorizeRequests()
			.antMatchers(AUTH_LIST).authenticated()
	        .and()                        
	        .httpBasic(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
	

}
