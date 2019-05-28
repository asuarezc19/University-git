package com.example.weather.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lluvia implements Serializable {

    @SerializedName("3h")
    @Expose
    private Double h;

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }
}
