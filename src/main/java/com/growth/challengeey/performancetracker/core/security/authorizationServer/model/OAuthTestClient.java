package com.growth.challengeey.performancetracker.core.security.authorizationServer.model;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("performance-tracker.oauth.client.test")
public class OAuthTestClient {
	
	@NotBlank
	private String clientName;
	
	@NotBlank
	private String clientSecret;

}
