package com.example.photoExchange.dto;

public class ImageDtoIn {
    private String base64Image;

    private int date;

    private double lat;
    private double lng;

    public ImageDtoIn(String base64Image, int date, double lat, double lng) {
        this.base64Image = base64Image;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public ImageDtoIn() {
    }

    public String getBase64Image() {
        return base64Image;
    }

    public int getDate() {
        return date;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
