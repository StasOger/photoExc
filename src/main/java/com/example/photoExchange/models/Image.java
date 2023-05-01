package com.example.photoExchange.models;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String base64Image;

    private long date;

    private double lat;

    private double lng;

    private String url;
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy="image", cascade = ALL)
    private List<Comment> comments;

    public Image(int id, String base64Image, long date, double lat, double lng, String url) {
        this.id = id;
        this.base64Image = base64Image;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public long getDate() {
        return date;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getUrl() {
        return url;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String
    toString() {
        return "Image{" +
                "id=" + id +
                ", base64Image='" + base64Image + '\'' +
                ", date=" + date +
                ", lat=" + lat +
                ", lng=" + lng +
                ", url='" + url + '\'' +
                '}';
    }
}
