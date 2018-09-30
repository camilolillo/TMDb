package camilolillo.peliculapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoPelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pelicula);
        iniciarWidgets();
    }

    private void iniciarWidgets(){

        TextView titulo = findViewById(R.id.infoTitulo);
        TextView calificacion = findViewById(R.id.infoCalificacion);
        TextView valoracion = findViewById(R.id.infoValoracion);
        TextView fecha = findViewById(R.id.infoFecha);
        TextView descripcion = findViewById(R.id.infoDescripcion);
        ImageView imagen = findViewById(R.id.infoImagen);

        titulo.setText(getIntent().getStringExtra("titulo"));
        calificacion.setText(getIntent().getStringExtra("calificacion"));
        valoracion.setText(getIntent().getStringExtra("valoracion"));
        fecha.setText(getIntent().getStringExtra("fecha"));
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        Picasso.get().load("https://image.tmdb.org/t/p/w200_and_h300_bestv2"+getIntent().getStringExtra("urlImagen")).into(imagen);

        Button btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoPelicula.this,MainActivity.class));
            }
        });
    }

}
