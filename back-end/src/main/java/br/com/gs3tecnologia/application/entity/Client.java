package br.com.gs3tecnologia.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column
	private String nome;

	@NotNull
	@Column(unique = true)
	private String cpf;

	@Column
	@NotNull
	private String cep;

	@Column
	@NotNull
	private String logradouro;

	@Column
	@NotNull
	private String bairro;

	@Column
	@NotNull
	private String cidade;

	@Column
	@NotNull
	private String uf;

	@Column
	private String complemento;

	@Column
	private String Celular;

	@Column
	private String Comercial;

	@Column
	private String Residencial;

	@NotNull
	@Column(unique = true)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String Celular) {
		this.Celular = Celular;
	}

	public String getComercial() {
		return Comercial;
	}

	public void setComercial(String Comercial) {
		this.Comercial = Comercial;
	}

	public String getResidencial() {
		return Residencial;
	}

	public void setResidencial(String Residencial) {
		this.Residencial = Residencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
