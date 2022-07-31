package com.prabhat.authentication.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "serviceConfig")
public class ServiceConfig {
	
	private boolean jwtEnabled;
	private String signingKey;
	
	public boolean isJwtEnabled() {
		return jwtEnabled;
	}
	public void setJwtEnabled(boolean jwtEnabled) {
		this.jwtEnabled = jwtEnabled;
	}
	public String getSigningKey() {
		return signingKey;
	}
	public void setSigningKey(String signingKey) {
		this.signingKey = signingKey;
	}
}
