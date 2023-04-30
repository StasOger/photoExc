package com.example.photoExchange.dto;

public class SignUserDtoIn {
    private String login;
    private String password;

    public SignUserDtoIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SignUserDtoIn{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
