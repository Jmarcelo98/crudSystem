package br.com.gs3tecnologia.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gs3tecnologia.application.entity.User;
import br.com.gs3tecnologia.application.factory.InterfaceUserService;
import br.com.gs3tecnologia.application.repository.UserRepository;

@Service
public class UserService implements InterfaceUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Object validarUsuario(String usuario, String senha) {

		User user = userRepository.findByUsernameAndPassword(usuario, senha);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
