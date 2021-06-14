package br.com.gs3tecnologia.application.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String obterToken() {
		return this.jwttoken;
	}

}
