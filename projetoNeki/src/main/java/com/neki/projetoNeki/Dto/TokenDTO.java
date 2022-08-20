package com.neki.projetoNeki.Dto;

public class TokenDTO {
	private String type;
	private String login;
	private String senha;
	private String token;
	private Integer id;

	public TokenDTO(String type, String token, String login, String senha, Integer id) {
		this.type = type;
		this.login = login;
		this.senha = senha;
		this.token = token;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getid() {
		return id;
	}

	public void setIdUsuairo(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
