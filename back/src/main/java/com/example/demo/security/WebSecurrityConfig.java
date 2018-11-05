package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.example.demo.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class WebSecurrityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;

	@Autowired
	private UsuarioRepository usuario;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//		http.csrf().disable().authorizeRequests()
		//		.antMatchers(HttpMethod.GET, "/").permitAll()
		//		.antMatchers(HttpMethod.GET, "/listar").hasRole("ADMIN")
		//		.antMatchers("/home").permitAll()
		//		.antMatchers(HttpMethod.POST, "/login").permitAll()
		//		.anyRequest().authenticated()
		//		.and().formLogin().permitAll()
		//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

		http.csrf().disable().authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and()

		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), usuario),
				UsernamePasswordAuthenticationFilter.class)

		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setExposedHeaders(Arrays.asList("Authorization", "X-Content-Type-Options", "X-XSS-Protection"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;

	}

}

