package br.com.desafio2.ilab.usuarios.models;

public class Token {
	private String token;

	public Token(String token) {
		super();
		this.token = token;
	}

	public Token() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String toString() {
		String[] tokenFormater = token.split(" ");
		return tokenFormater[1];
	}
}