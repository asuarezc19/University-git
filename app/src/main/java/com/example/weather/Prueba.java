package com.example.weather;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Prueba {

    @GET("weather?q=London&appid=d594165f2026dbcb4604c51cfc32893a")
    Call<ResponseBody> prueba();

    @GET("file/d/1EvRoEUa5yCEoafMUg4XOx9SgW3joL1s4/view")
    Call<ResponseBody> cities();

    @GET("weather")
    Call<ResponseBody> historical(@Query("id") int id, @Query("appid") String x);
}
