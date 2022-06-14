package com.thoughtmechanix.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoughtmechanix.authentication.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	Optional<Client> findByClientId(String clientId);

}
