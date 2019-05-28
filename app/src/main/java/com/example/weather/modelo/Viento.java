package com.example.weather.modelo;

import java.io.Serializable;

public class Viento implements Serializable {

    private Double speed;
    private int deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
