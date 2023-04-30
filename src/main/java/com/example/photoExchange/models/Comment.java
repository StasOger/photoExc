package com.example.photoExchange.models;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long date;

    private String text;
    @ManyToOne
    @JoinColumn(name="image_id", nullable=false)
    private Image image;

    public Comment(int id, long date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public Comment() {
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
