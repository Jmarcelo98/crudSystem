package br.com.gs3tecnologia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.entity.Authenticar;
import br.com.gs3tecnologia.application.factory.InterfaceAuthenticarService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticarController {

	@Autowired
	private InterfaceAuthenticarService interfaceUserService;

	@SuppressWarnings("unchecked")
	@GetMapping("{username}/{password}")
	public @ResponseBody ResponseEntity<Authenticar> buscarUsuario(@PathVariable(value = "username") String username,
			@PathVariable(value = "password") String password) {
		return (ResponseEntity<Authenticar>) interfaceUserService.findByUsernameAndPassword(username, password);
	}

}