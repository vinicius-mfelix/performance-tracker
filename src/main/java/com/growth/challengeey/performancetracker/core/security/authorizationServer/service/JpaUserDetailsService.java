package com.growth.challengeey.performancetracker.core.security.authorizationServer.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.challengeey.performancetracker.core.security.authorizationServer.model.AuthUser;
import com.growth.challengeey.performancetracker.domain.model.User;
import com.growth.challengeey.performancetracker.domain.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário com email informado não encontrado."));
		
		return new AuthUser(user, getAuthorities(user));
	}
	
	public Collection<GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream()
				.flatMap(role -> role.getPermissions().stream())
				.map(permission -> new SimpleGrantedAuthority(permission.getName().toUpperCase()))
				.collect(Collectors.toSet());
	}

}
