package br.com.gs3tecnologia.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gs3tecnologia.application.entity.Client;
import br.com.gs3tecnologia.application.factory.InterfaceClientService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")
public class ClientController {

	@Autowired
	private InterfaceClientService interfaceClientService;

	@GetMapping
	public List<Client> consultarTodos() {
		return interfaceClientService.consultarTodos();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Client> consultarPeloId(@PathVariable(value = "id") long id) {
		return interfaceClientService.consultarPeloId(id);
	}

//	@GetMapping(path = "email/{email}")
//	public @ResponseBody ResponseEntity<Boolean> procurarPeloEmail(String email) {
//		return interfaceClientService.consultarPeloEmail(email);
//	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "email/{email}")
	public @ResponseBody ResponseEntity<Client> procurarPeloEmail(@PathVariable(value = "email") String email) {
		return (ResponseEntity<Client>) interfaceClientService.consultarPeloEmail(email);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "cpf/{cpf}")
	public @ResponseBody ResponseEntity<Client> procurarPeloCpf(@PathVariable(value = "cpf") String cpf) {
		return (ResponseEntity<Client>) interfaceClientService.consultarPeloCpf(cpf);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro Realizado com Sucesso"),
			@ApiResponse(code = 400, message = "Erro ao Cadastrar"),
			@ApiResponse(code = 403, message = "	Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping
	public @ResponseBody Client salvar(@Valid @RequestBody Client cliente) {
		return interfaceClientService.salvar(cliente);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro Atualizado com Sucesso"),
			@ApiResponse(code = 400, message = "Erro ao Atualizar"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PutMapping(path = "/{id}")
	public ResponseEntity<Client> atualizar(@Valid @PathVariable(value = "id") long id,
			@Valid @RequestBody Client novoCliente) {
		return interfaceClientService.atualizar(id, novoCliente);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão Realizado com Sucesso"),
			@ApiResponse(code = 400, message = "Erro ao Excluir/Usuario Não Existe na Base de Dados"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id") long id) {
		return interfaceClientService.deletar(id);
	}

}
