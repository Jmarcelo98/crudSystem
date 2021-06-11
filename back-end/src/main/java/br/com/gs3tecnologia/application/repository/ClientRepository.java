package br.com.gs3tecnologia.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gs3tecnologia.application.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{	

	
}