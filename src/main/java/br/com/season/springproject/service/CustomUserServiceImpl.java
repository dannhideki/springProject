package br.com.season.springproject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.entity.UserProfile;

@Service("customUserServiceImpl")
public class CustomUserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Username not found!");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantAuthority(user));
	}

	private Collection<? extends GrantedAuthority> getGrantAuthority(User user) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+ userProfile.getType()));
		}
		return authorities;
	}
	
	
	
	
	
	
	
	

}
