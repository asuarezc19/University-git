package com.example.weather;

import com.example.weather.modelo.Data;

public class City {

    private String city, temperture, note;

    public City(String city, String temperture, String note) {
        this.city = city;
        this.temperture = temperture;
        this.note = note;
    }

    public String getName() {
        return city;
    }

    public void setName(String name) {
        this.city = name;
    }

    public String getTemperture() {
        return temperture;
    }

    public void setTemperture(String temperture) {
        this.temperture = temperture;
    }

    public String getNote() {
        return note;
    }

    public void save(){
        Data.save(this);
    }


}
