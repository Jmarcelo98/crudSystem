package br.com.gs3tecnologia.application.factory;

public interface InterfaceAuthenticarService {

	Object findByUsernameAndPassword(String username, String senha);
	
}
