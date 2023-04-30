package com.example.photoExchange.dto;

public class SignUserOutDto {
    private int userId;
    private String login;
    private String token;

    public SignUserOutDto(int userId, String login, String token) {
        this.userId = userId;
        this.login = login;
        this.token = token;
    }

    public SignUserOutDto() {
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SignUserOutDto{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
