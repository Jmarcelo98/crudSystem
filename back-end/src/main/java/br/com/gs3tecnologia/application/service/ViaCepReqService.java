package br.com.gs3tecnologia.application.service;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.gs3tecnologia.application.factory.InterfaceViaCepApi;

@Component
public class ViaCepReqService implements InterfaceViaCepApi {
	@Override
	public String buscarPorCep(String cep) {

		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET,
				new HttpEntity<String>(createHeaders("user-public-notificacoes", "Za4qNXdyQNSa9YaA")), String.class);
		return response.getBody();
	}

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
