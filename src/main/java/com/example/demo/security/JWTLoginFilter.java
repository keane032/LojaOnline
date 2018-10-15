package com.example.demo.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	UsuarioRepository repository;
	
	protected JWTLoginFilter(String url, AuthenticationManager authManager, UsuarioRepository repository) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.repository = repository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		AppUser user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		
		Usuario usuario = repository.findByLogin(user.getLogin());
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		
		else if(!BCrypt.checkpw(user.getSenha(), usuario.getSenha())) {
			throw new BadCredentialsException("Invalid username/password");
		}
		
	    return new UsernamePasswordAuthenticationToken(user, usuario.getSenha(), Collections.emptyList());
		
		
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenAuthenticationService.addAuthentication(response, auth.getName(),this.repository);
	}

}