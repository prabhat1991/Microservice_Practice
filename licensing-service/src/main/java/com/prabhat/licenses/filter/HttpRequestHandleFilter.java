//package com.thoughtmechanix.licenses.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//public class HttpRequestHandleFilter extends OncePerRequestFilter {
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		
//		final String authorizationHeader = request.getHeader("Authorization");
//
//		String username = null;
//		String jwt = null;
//
//		if (authorizationHeader != null && authorizationHeader.startsWith("bearer ")) {
//			jwt = authorizationHeader.substring(7);
//		}
//		
//		OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//		System.out.println(authDetails);
//	}
//
//}
