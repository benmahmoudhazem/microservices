package com.talanlabs.microservices.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

	/**
	 * Provide security so that endpoints are only served if the request is
	 * already authenticated.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.requestMatchers()
				.antMatchers("/**")
				.and()
				.authorizeRequests()
					.antMatchers("/manage/**").permitAll()		//FIXME: secure /manage/**
					.anyRequest().authenticated();
		// @formatter:on
	}
}
