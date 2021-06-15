package br.com.gs3tecnologia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.factory.InterfaceViaCepApi;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ViaCepApi {

	@Autowired
	private InterfaceViaCepApi viaCepApi;

	@GetMapping(path = "/{cep}")
	public @ResponseBody String pegarDadosApi(@PathVariable(value = "cep") String cep) {
		return viaCepApi.buscarPorCep(cep);
	}

}
