package br.com.gs3tecnologia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.entity.User;
import br.com.gs3tecnologia.application.factory.InterfaceUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private InterfaceUserService interfaceUserService;

	@SuppressWarnings("unchecked")
	@GetMapping
	public @ResponseBody ResponseEntity<User> buscarUsuario(String username, String senha) {
		return (ResponseEntity<User>) interfaceUserService.findByUsernameAndPassword(username, senha);
	}
	
}