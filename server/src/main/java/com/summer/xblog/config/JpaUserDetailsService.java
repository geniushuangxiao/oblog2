package com.summer.xblog.config;

import com.summer.xblog.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JpaUserDetailsService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(JpaUserDetailsService.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return userRepository.findByUsername(username).get(0);
		} catch (Exception e) {
			String errMessage = String.format("An error occurred while executing userRepository.findByUsername(%s)",
					username);
			log.error(errMessage, e);
			throw new UsernameNotFoundException(errMessage, e);
		}
	}

}
