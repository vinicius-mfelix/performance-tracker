package com.growth.challengeey.performancetracker.core.security.authorizationServer.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.growth.challengeey.performancetracker.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;

	public AuthUser(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

}
