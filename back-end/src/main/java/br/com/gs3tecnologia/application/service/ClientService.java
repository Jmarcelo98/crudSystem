package br.com.gs3tecnologia.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gs3tecnologia.application.entity.Client;
import br.com.gs3tecnologia.application.factory.InterfaceClientService;
import br.com.gs3tecnologia.application.repository.ClientRepository;

@Service
public class ClientService implements InterfaceClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> consultarTodos() {
		return clientRepository.findAll();
	}

	@Override
	public ResponseEntity<Client> consultarPeloId(Long id) {

		Optional<Client> cliente = clientRepository.findById(id);

		if (cliente.isPresent()) {
			return new ResponseEntity<Client>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Client salvar(Client cliente) {
		return clientRepository.save(cliente);
	}

	@Override
	public ResponseEntity<Client> atualizar(Long id, Client novoCliente) {
		clientRepository.save(novoCliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deletar(Long id) {

		Optional<Client> client = clientRepository.findById(id);

		if (client.isPresent()) {
			clientRepository.delete(client.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

//	@Override
//	public ResponseEntity<Object> buscarCepApi(String cep) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@GetMapping(value = "/cliente")
//    public @ResponseBody List<Pessoa> consultar() {
//
//        return pessoaService.consultar();
//    }

}