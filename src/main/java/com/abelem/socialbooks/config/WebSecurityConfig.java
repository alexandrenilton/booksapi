package com.abelem.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// qual mecanismo para autenticar?
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("abelem")
			.password("{noop}abelem")
			.roles("USER")
			.and().withUser("admin").password("{noop}admin").roles("SYSTEM"); 
			// Yeah.. I don't know why we should add {noop} to password string value..
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers("/h2-console/**").permitAll() // skip the h2-console app
			.anyRequest().authenticated() // qualquer requisição tem q estar autenticada
			.and()
				.httpBasic() // usar o método basico de autenticação do http;
			.and()
				.csrf().disable(); //tipo de proteção para evitar um ataque específico.. desabilita se for api para uso de terceiros
	}
}
