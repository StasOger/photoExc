package com.example.photoExchange.dto;

public class CommentDtoIn {

    private String text;

    public CommentDtoIn(String text) {
        this.text = text;
    }

    public CommentDtoIn() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentDtoIn{" +
                "text='" + text + '\'' +
                '}';
    }
}
