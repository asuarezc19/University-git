package com.example.weather.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Respuesta implements Serializable {

    private Coordenadas coord;
    private ArrayList<Clima> weather;
    private String base;
    private Principal main;
    private int visibility;
    private Viento wind;
    private Lluvia rain;
    private Nubes clouds;
    private long dt;
    private Sistema sys;
    private int timezone;
    private long id;
    private String name;
    private int cod;

    public Coordenadas getCoord() {
        return coord;
    }

    public void setCoord(Coordenadas coord) {
        this.coord = coord;
    }

    public ArrayList<Clima> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Clima> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Principal getMain() {
        return main;
    }

    public void setMain(Principal main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Viento getWind() {
        return wind;
    }

    public void setWind(Viento wind) {
        this.wind = wind;
    }

    public Lluvia getRain() {
        return rain;
    }

    public void setRain(Lluvia rain) {
        this.rain = rain;
    }

    public Nubes getClouds() {
        return clouds;
    }

    public void setClouds(Nubes clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sistema getSys() {
        return sys;
    }

    public void setSys(Sistema sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
