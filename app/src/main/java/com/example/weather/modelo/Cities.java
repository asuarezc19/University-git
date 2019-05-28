package com.example.weather.modelo;

import android.os.Parcelable;

import java.io.Serializable;

public class Cities implements Serializable {

    private int id;
    private String country;
    private String name;
    private Coordenadas coord;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordenadas getCoord() {
        return coord;
    }

    public void setCoord(Coordenadas coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return name;
    }
}
