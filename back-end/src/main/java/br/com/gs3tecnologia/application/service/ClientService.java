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
			
			if (elemento.getCelular() != null) {
				String celularFormatado = formatString(elemento.getCelular(), "(##) #####-####");
				elemento.setCelular(celularFormatado);
			}
			if (elemento.getComercial() != null) {
				String comercialFormatado = formatString(elemento.getComercial(), "(##) ####-####");
				elemento.setComercial(comercialFormatado);
			} if (elemento.getResidencial() != null) {
				String resedencialFormatado = formatString(elemento.getResidencial(), "(##) ####-####");
				elemento.setResidencial(resedencialFormatado);
			}
		

			elemento.setCpf(cpfFormatado);
			elemento.setCep(cepFormatado);
			

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

	@Override
	public Client salvar(Client cliente) {

		
		if (cliente.getComplemento() == null) {
			cliente.setComplemento("");
		}
		if (cliente.getCelular() == null) {
			cliente.setCelular("");
		}
		if (cliente.getComercial() == null) {
			cliente.setComercial("");
		}
		if (cliente.getResidencial() == null) {
			cliente.setResidencial("");
		}

		toUpperCase(cliente);

		if (cliente.getComplemento() == "") {
			cliente.setComplemento(null);
		}
		if (cliente.getCelular() == "") {
			cliente.setCelular(null);
		}
		if (cliente.getComercial() == "") {
			cliente.setComercial(null);
		}
		if (cliente.getResidencial() == "") {
			cliente.setResidencial(null);
		}

		String replaceCep = cliente.getCep().replace("-", "");
		String replaceCpf = cliente.getCpf().replace(".", "").replace("-", "");

		if (cliente.getCelular() != null) {
			String replaceTelefone = cliente.getCelular().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			cliente.setCelular(replaceTelefone);
		}
		if (cliente.getComercial() != null) {
			String replaceTelefone2 = cliente.getComercial().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			cliente.setComercial(replaceTelefone2);
		}
		if (cliente.getResidencial() != null) {
			String replaceTelefone3 = cliente.getResidencial().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			cliente.setResidencial(replaceTelefone3);
		}

		cliente.setCep(replaceCep);
		cliente.setCpf(replaceCpf);

		return clientRepository.save(cliente);
	}

	@Override
	public ResponseEntity<Client> atualizar(Long id, Client novoCliente) {

		if (novoCliente.getComplemento() == null) {
			novoCliente.setComplemento("");
		}
		if (novoCliente.getCelular() == null) {
			novoCliente.setCelular("");
		}
		if (novoCliente.getComercial() == null) {
			novoCliente.setComercial("");
		}
		if (novoCliente.getResidencial() == null) {
			novoCliente.setResidencial("");
		}

		toUpperCase(novoCliente);

		if (novoCliente.getComplemento() == "") {
			novoCliente.setComplemento(null);
		}
		if (novoCliente.getCelular() == "") {
			novoCliente.setCelular(null);
		}
		if (novoCliente.getComercial() == "") {
			novoCliente.setComercial(null);
		}
		if (novoCliente.getResidencial() == "") {
			novoCliente.setResidencial(null);
		}
		
		if (novoCliente.getCelular() != null) {
			String replaceTelefone = novoCliente.getCelular().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			novoCliente.setCelular(replaceTelefone);
		}
		if (novoCliente.getComercial() != null) {
			String replaceTelefone2 = novoCliente.getComercial().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			novoCliente.setComercial(replaceTelefone2);
		}
		if (novoCliente.getResidencial() != null) {
			String replaceTelefone3 = novoCliente.getResidencial().replace("(", "").replace(")", "").replace(" ", "")
					.replace("-", "");
			novoCliente.setResidencial(replaceTelefone3);
		}


		String replaceCep = novoCliente.getCep().replace("-", "");
		String replaceCpf = novoCliente.getCpf().replace(".", "").replace("-", "");

		novoCliente.setCep(replaceCep);
		novoCliente.setCpf(replaceCpf);

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