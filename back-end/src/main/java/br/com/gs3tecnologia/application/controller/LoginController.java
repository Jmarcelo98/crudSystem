package br.com.gs3tecnologia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.entity.Login;
import br.com.gs3tecnologia.application.factory.InterfaceLoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private InterfaceLoginService interfaceUserService;

	@SuppressWarnings("unchecked")
	@GetMapping
	public @ResponseBody ResponseEntity<Login> buscarUsuario(String username, String senha) {
		return (ResponseEntity<Login>) interfaceUserService.findByUsernameAndPassword(username, senha);
	}

}