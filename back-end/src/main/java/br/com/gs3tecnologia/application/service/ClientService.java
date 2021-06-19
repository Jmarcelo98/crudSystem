package br.com.gs3tecnologia.application.service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.swing.text.MaskFormatter;

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

		List<Client> teste = clientRepository.findAll();

		for (int i = 0; i < teste.size(); i++) {

			Client elemento = teste.get(i);

			String cpfFormatado = formatString(elemento.getCpf(), "###.###.###-##");
			String cepFormatado = formatString(elemento.getCep(), "#####-###");
			String telefonesFormatado = formatString(elemento.getTelefones(), "(##) #####-####");

			elemento.setCpf(cpfFormatado);
			elemento.setCep(cepFormatado);
			elemento.setTelefones(telefonesFormatado);

		}

		return teste;
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
	public Object consultarPeloCpf(String cpf) {

		String cpfFormatado = cpf.replace(".", "").replace("-", "");
		
		Client cliente = clientRepository.findByCpf(cpfFormatado);
		
		if (cliente != null) {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public Object consultarPeloEmail(String email) {

		String emailUpper = email.toUpperCase();
		
		Client cliente = (Client) clientRepository.findByEmail(emailUpper);
		
		if (cliente != null) {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

//	@Override
//	public ResponseEntity<Boolean> consultarPeloEmail(String email) {
//
//		String emailUpper = email.toUpperCase();
//		
//		Client client = (Client) clientRepository.findByEmail(emailUpper);
//		
//		if (client == null) {
//			return new ResponseEntity<>(false, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(true, HttpStatus.OK);
//		}
//	}

	@Override
	public Client salvar(Client cliente) {
		
//		Object consultaEmail = clientRepository.findByEmail(cliente.getEmail());
//		Object consultaCpf = clientRepository.findByCpf(cliente.getCpf());
//		System.out.println(consultaCpf);
//		System.out.println(consultaEmail);
		
//		if (consultaEmail != null) {
//			return new ResponseEntity<>(false+"email", HttpStatus.BAD_REQUEST);
//		} else if (consultaCpf != null) {
//			return new ResponseEntity<>(false+"cpf", HttpStatus.BAD_REQUEST);
//		} else {
			
			if (cliente.getComplemento() == null) {
				cliente.setComplemento("");
			}

			toUpperCase(cliente);

			if (cliente.getComplemento() == "") {
				cliente.setComplemento(null);
			}

			String replaceCep = cliente.getCep().replace("-", "");
			String replaceCpf = cliente.getCpf().replace(".", "").replace("-", "");
			String replaceTelefones = cliente.getTelefones().replace("(", "").replace(")", "").replace(" ", "").replace("-",
					"");

			cliente.setCep(replaceCep);
			cliente.setCpf(replaceCpf);
			cliente.setTelefones(replaceTelefones);

			return clientRepository.save(cliente);
//			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
		
		
//	}

	@Override
	public ResponseEntity<Client> atualizar(Long id, Client novoCliente) {

		if (novoCliente.getComplemento() == null) {
			novoCliente.setComplemento("");
		}

		toUpperCase(novoCliente);

		if (novoCliente.getComplemento() == "") {
			novoCliente.setComplemento(null);
		}

		String replaceCep = novoCliente.getCep().replace("-", "");
		String replaceCpf = novoCliente.getCpf().replace(".", "").replace("-", "");
		String replaceTelefones = novoCliente.getTelefones().replace("(", "").replace(")", "").replace(" ", "")
				.replace("-", "");

		novoCliente.setCep(replaceCep);
		novoCliente.setCpf(replaceCpf);
		novoCliente.setTelefones(replaceTelefones);

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

	public static void toUpperCase(Object obj) {
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getType().equals(String.class)) {
					f.set(obj, f.get(obj).toString().toUpperCase());
				}
			}
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
		}
	}

	public String formatString(String value, String pattern) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(pattern);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException ex) {
			return value;
		}
	}

}