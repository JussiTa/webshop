package fi.webshop.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fi.webshop.users.dao.UserDao;
import fi.webshop.users.model.UserRole;

public class MyUserDetailsService implements UserDetailsService {
	/*
	 * This class is for getting and transferring User to spring framework
	 * userdetailuser
	 * 
	 * 
	 */

	private UserDao userDao;
	

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		

		fi.webshop.users.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user
				.getRoles());
		
		
		return buildUserForAuthentication(user, authorities);

	}

	// Converts fi.webshop.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(fi.webshop.users.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
		
	

}