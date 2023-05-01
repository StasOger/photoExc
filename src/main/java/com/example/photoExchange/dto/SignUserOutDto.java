package com.example.photoExchange.dto;

import java.util.List;

public class SignUserOutDto {

	private Long id;
	private String username;
	private String token;
	private String type = "Bearer";
	private List<String> roles;

	public SignUserOutDto(Long id, String username, String token, List<String> roles) {
		this.id = id;
		this.username = username;
		this.token = token;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
