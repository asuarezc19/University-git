package com.example.weather.modelo;

import com.example.weather.City;

import java.util.ArrayList;

public class Data {

    private static ArrayList<City> cities = new ArrayList<>();
    public static void save (City c){
        cities.add(c);}
    public static ArrayList<City> getCities(){return cities;}

}
