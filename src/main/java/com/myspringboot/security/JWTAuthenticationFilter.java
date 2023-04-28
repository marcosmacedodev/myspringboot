package com.myspringboot.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringboot.model.dto.CredenciaisDTO;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager am;
	private JwtUtil ju;
	
	public JwtAuthenticationFilter(AuthenticationManager am, JwtUtil ju) {
		this.am = am;
		this.ju = ju;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException{
		try {
		CredenciaisDTO creds = new ObjectMapper().
				readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
			Authentication auth = am.authenticate(authToken);
			return auth;
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = ju.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
	}
	
	
}
