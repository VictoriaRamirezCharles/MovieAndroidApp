package com.victoriaramirezcharles.pelicula;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import static com.victoriaramirezcharles.pelicula.Inicio.EXTRA_URL;
import static com.victoriaramirezcharles.pelicula.Inicio.EXTRA_TITULO;
public class DetallePelicula extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    AppCompatButton appCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITULO);
        agregarFAB();
        youtube();

        imageView = findViewById(R.id.imgDetalles);
        textView = findViewById(R.id.tituloDetalles);
        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(title);
    }



    private void youtube() {
        appCompatButton = findViewById(R.id.apb2);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9u2QR4vyMZI&feature=youtu.be")));

            }

        });


    }
    public void agregarFAB() {
        FloatingActionButton miFAB = findViewById(R.id.fb1);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getBaseContext(), getResources().getString(R.string.favorito),Toast.LENGTH_SHORT).show();
                Snackbar.make(view, getResources().getString(R.string.favorito), Snackbar.LENGTH_SHORT)
                        .setAction(getResources().getString(R.string.accion), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("SnackBar", "Click");
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .show();
            }
        });

    }

}
