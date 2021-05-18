package com.growth.challengeey.performancetracker.core.security.authorizationServer.configuration;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.growth.challengeey.performancetracker.core.security.authorizationServer.model.AuthUser;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		if (authentication.getPrincipal() instanceof AuthUser) {
			AuthUser user = (AuthUser) authentication.getPrincipal();
			
			HashMap<String, Object> payload = new HashMap<>();
			payload.put("user_id", user.getId());
			payload.put("user_name", user.getName());
			payload.put("user_email", user.getEmail());
			
			DefaultOAuth2AccessToken oauth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
			oauth2AccessToken.setAdditionalInformation(payload);
		}
		
		return accessToken;
		
	}

}
