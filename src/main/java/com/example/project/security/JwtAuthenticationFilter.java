package com.example.project.security;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.UserAuthService;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	UserAuthService userService;

	@Autowired
	JwtUtil util;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getToken(request);
		
		if(token != null && util.validateToken(token) &&  SecurityContextHolder.getContext().getAuthentication() == null) {
			
			String username = util.getUserIdFromJwt(token);
			UserDetails details = userService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					details, null, Collections.emptyList());

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);


		}
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken !=  null  && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7);
		}
		return null;
	}
	
	

}