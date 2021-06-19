package br.com.gs3tecnologia.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gs3tecnologia.application.entity.Authenticar;

@Repository

public interface AuthenticarRepository extends JpaRepository<Authenticar, Long> {

	Authenticar findByUsernameAndPassword(String username, String senha);

}
