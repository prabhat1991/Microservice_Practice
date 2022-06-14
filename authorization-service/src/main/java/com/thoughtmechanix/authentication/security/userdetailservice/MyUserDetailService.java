package com.thoughtmechanix.authentication.security.userdetailservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thoughtmechanix.authentication.model.User;
import com.thoughtmechanix.authentication.repository.UserRepository;
import com.thoughtmechanix.authentication.security.userdetails.MyUserDetails;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		optionalUser.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		return optionalUser.map(MyUserDetails::new).get();
	}

}
