package br.com.gs3tecnologia.application.factory;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.gs3tecnologia.application.entity.Client;

public interface InterfaceClientService {
	
	List<Client> consultarTodos();

	ResponseEntity<Client> consultarPeloId(Long id);

	Client salvar (Client cliente);

	ResponseEntity<Client> atualizar(Long id, Client novoCliente);

	ResponseEntity<Object> deletar(Long id);
	
	ResponseEntity<Object> buscarCepApi(String cep);

}
