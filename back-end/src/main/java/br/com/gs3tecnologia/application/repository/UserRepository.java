package br.com.gs3tecnologia.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gs3tecnologia.application.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String senha);

}
