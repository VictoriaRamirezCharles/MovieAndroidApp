package com.victoriaramirezcharles.pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {
    @GET("volley_array.json")
    Call<List<Pelicula>> getPeliculas();
}
