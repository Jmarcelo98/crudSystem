package br.com.gs3tecnologia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.factory.InterfaceViaCepApi;

@RestController
@RequestMapping("/api")
public class ViaCepApi {

	@Autowired
	private InterfaceViaCepApi viaCepApi;

	@GetMapping
	public @ResponseBody String pegarDadosApi(String cep) {
		return viaCepApi.buscarPorCep(cep);
	}

}
