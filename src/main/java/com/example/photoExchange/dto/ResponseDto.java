package com.example.photoExchange.dto;

public class ResponseDto {
	
	private String status;

	public ResponseDto(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
