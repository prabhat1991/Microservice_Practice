package com.thoughtmechanix.authentication.security.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.thoughtmechanix.authentication.model.Client;

public class MyClientDetails implements ClientDetails {
	
	
	private String clientId;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String redirectURI;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private boolean autoapprove;
	
	public MyClientDetails(Client client){
		this.clientId  = client.getClientId();
		this.clientSecret  = client.getClientSecret();
		this.scope  = client.getScope();
		this.authorizedGrantTypes  = client.getAuthorizedGrantTypes();
		this.redirectURI  = client.getRedirectURI();
		this.authorities  = client.getAuthorities();
		this.accessTokenValidity  = client.getAccessTokenValidity();
		this.refreshTokenValidity  = client.getRefreshTokenValidity();
		this.additionalInformation  = client.getAdditionalInformation();
		this.autoapprove  = client.isAutoapprove();
		
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return null;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return Arrays.stream(scope.split(",")).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return Arrays.stream(authorizedGrantTypes.split(",")).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return Arrays.stream(redirectURI.split(",")).collect(Collectors.toSet());
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return autoapprove;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}
