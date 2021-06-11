package br.com.gs3tecnologia.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{name.not.blank}")
	@NotNull(message = "{name.not.null}")
	@Column
	private String nome;

	@NotBlank(message = "{cpf.not.blank}")
	@NotNull(message = "{cpf.not.null}")
	@Column(unique = true)
	private String cpf;

	@NotBlank(message = "{cep.not.blank}")
	@NotNull(message = "{cep.not.null}")
	@Column
	private String cep;

	@NotBlank(message = "{logradouro.not.blank}")
	@NotNull(message = "{logradouro.not.null}")
	@Column
	private String logradouro;

	@NotBlank(message = "{bairro.not.blank}")
	@NotNull(message = "{bairro.not.null}")
	@Column
	private String bairro;

	@NotBlank(message = "{cidade.not.blank}")
	@NotNull(message = "{cidade.not.null}")
	@Column
	private String cidade;

	@NotBlank(message = "{uf.not.blank}")
	@NotNull(message = "{uf.not.null}")
	@Column
	private String uf;

	@Column
	private String complemento;

	@NotBlank(message = "{telefones.not.blank}")
	@NotNull(message = "{telefones.not.null}")
	@Column
	private String telefones;

	@NotBlank(message = "{email.not.blank}")
	@NotNull(message = "{email.not.null}")
	@Email(message = "{email.not.valid}")
	@Column
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

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
