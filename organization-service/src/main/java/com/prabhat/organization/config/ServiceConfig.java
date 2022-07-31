package com.prabhat.organization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "serviceConfig")
public class ServiceConfig {
	
	private boolean jwtEnabled;
	private String signingKey;
	private String zuulLicensingserviceUrl;
	private String zuulOrganizationserviceUrl;
	
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
	public String getZuulLicensingserviceUrl() {
		return zuulLicensingserviceUrl;
	}
	public void setZuulLicensingserviceUrl(String zuulLicensingserviceUrl) {
		this.zuulLicensingserviceUrl = zuulLicensingserviceUrl;
	}
	public String getZuulOrganizationserviceUrl() {
		return zuulOrganizationserviceUrl;
	}
	public void setZuulOrganizationserviceUrl(String zuulOrganizationserviceUrl) {
		this.zuulOrganizationserviceUrl = zuulOrganizationserviceUrl;
	}
	
}
