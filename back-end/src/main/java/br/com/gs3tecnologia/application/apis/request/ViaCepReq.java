package br.com.gs3tecnologia.application.apis.request;

import java.io.Serializable;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepReq implements Serializable {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
//	@Bean
//    public String run(String cep) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://viacep.com.br/ws/"+cep+"/json/";
//            ResponseEntity<String> susApiResponse =
//                    restTemplate.exchange(url, HttpMethod.GET,
//                            new HttpEntity<String>(
//                                    createHeaders("user-public-notificacoes",
//                                            "Za4qNXdyQNSa9YaA")), String.class);
//
//            return  susApiResponse.getBody();
//    }


}
