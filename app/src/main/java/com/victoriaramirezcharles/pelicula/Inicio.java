package com.victoriaramirezcharles.pelicula;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio extends AppCompatActivity implements  Adaptador.OnItemClickListener{
    List<Pelicula> listaPeliculas;
    RecyclerView recyclerView;
    Adaptador recyclerAdapter;
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITULO = "title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listaPeliculas = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new Adaptador(getApplicationContext(), listaPeliculas);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemListener(Inicio.this);

        InterfaceApi apiService = Api.getApi().create(InterfaceApi.class);
        Call<List<Pelicula>> call = apiService.getPeliculas();
        call.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                listaPeliculas = response.body();
                Log.d("TAG", "Response = " + listaPeliculas);
                recyclerAdapter.setListaPeliculas(listaPeliculas);
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetallePelicula.class);
        Pelicula clickedItem = listaPeliculas.get(position);

        intent.putExtra(EXTRA_TITULO, clickedItem.getTitle());
        intent.putExtra(EXTRA_URL, clickedItem.getImageUrl());


        startActivity(intent);
    }
}

