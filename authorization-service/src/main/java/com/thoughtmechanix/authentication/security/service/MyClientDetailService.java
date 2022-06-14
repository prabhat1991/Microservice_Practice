package com.thoughtmechanix.authentication.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.thoughtmechanix.authentication.model.Client;
import com.thoughtmechanix.authentication.model.User;
import com.thoughtmechanix.authentication.repository.ClientRepository;
import com.thoughtmechanix.authentication.repository.UserRepository;
import com.thoughtmechanix.authentication.security.dto.MyClientDetails;
import com.thoughtmechanix.authentication.security.dto.MyUserDetails;

@Service
public class MyClientDetailService implements ClientDetailsService {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Optional<Client> optionalClient = clientRepository.findByClientId(clientId);
		
		optionalClient.orElseThrow(()->new UsernameNotFoundException("Client Not Found"));
		return optionalClient.map(MyClientDetails::new).get();
	}

}
