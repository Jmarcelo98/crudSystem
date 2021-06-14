package br.com.gs3tecnologia.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gs3tecnologia.application.entity.Login;
import br.com.gs3tecnologia.application.factory.InterfaceLoginService;
import br.com.gs3tecnologia.application.repository.LoginRepository;

@Service
public class LoginService implements InterfaceLoginService {

	@Autowired
	private LoginRepository userRepository;

	@Override
	public Object findByUsernameAndPassword(String username, String senha) {
		
		Login user = userRepository.findByUsernameAndPassword(username, senha);
		
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
