package com.example.photoExchange.dto;

public class ImageDtoOut {
    private int id;
    private String url;
    private long date;
    private double lat;
    private double lng;

    public ImageDtoOut(int id, String url, long date, double lat, double lng) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
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

    @Override
    public String toString() {
        return "ImageDtoOut{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", date=" + date +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
