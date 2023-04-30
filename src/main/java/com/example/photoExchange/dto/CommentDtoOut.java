package com.example.photoExchange.dto;

public class CommentDtoOut {
    private int id;
    private long date;
    private String text;

    public CommentDtoOut(int id, long date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "CommentDtoOut{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
