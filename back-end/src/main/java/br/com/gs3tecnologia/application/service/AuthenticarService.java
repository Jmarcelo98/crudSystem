package br.com.gs3tecnologia.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gs3tecnologia.application.entity.Authenticar;
import br.com.gs3tecnologia.application.factory.InterfaceAuthenticarService;
import br.com.gs3tecnologia.application.repository.AuthenticarRepository;

@Service
public class AuthenticarService implements InterfaceAuthenticarService {

	@Autowired
	private AuthenticarRepository authenticarRepository;

	@Override
	public Object findByUsernameAndPassword(String username, String senha) {
		
		Authenticar user = authenticarRepository.findByUsernameAndPassword(username, senha);
		
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
}
