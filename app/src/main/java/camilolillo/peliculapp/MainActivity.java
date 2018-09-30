package camilolillo.peliculapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity implements PeliculaAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private ArrayList<Pelicula> peliculas;
    private RequestQueue requestQueue;

    private String urlMejorEvaluadas = "https://api.themoviedb.org/3/movie/top_rated?api_key=34738023d27013e6d1b995443764da44";
    private String urlMasPopulares = "https://api.themoviedb.org/3/movie/popular?api_key=34738023d27013e6d1b995443764da44";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarWidgets();
    }

    private void iniciarWidgets() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        peliculas = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        Button btnPopulares = findViewById(R.id.btnPopulares);
        Button btnEvaluadas = findViewById(R.id.btnEvaluadas);
        btnPopulares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peliculas.clear();
                parseJSON(urlMasPopulares);
            }
        });
        btnEvaluadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                peliculas.clear();
                parseJSON(urlMejorEvaluadas);
            }
        });
    }

    private void parseJSON(final String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject result = jsonArray.getJSONObject(i);
                                int votos = result.getInt("vote_count");
                                int id = result.getInt("id");
                                double calificacion = result.getDouble("vote_average");
                                String titulo = result.getString("title");
                                double popularidad = result.getDouble("popularity");
                                String urlImagen = result.getString("poster_path");
                                String descripcion = result.getString("overview");
                                String fecha = result.getString("release_date");
                                peliculas.add(new Pelicula(votos,id,calificacion,titulo,popularidad,urlImagen,descripcion,fecha));
                            }
                            peliculaAdapter = new PeliculaAdapter(MainActivity.this,peliculas);
                            recyclerView.setAdapter(peliculaAdapter);
                            peliculaAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,InfoPelicula.class);
        intent.putExtra("titulo",peliculas.get(position).getTitulo());
        intent.putExtra("calificacion",String.valueOf(peliculas.get(position).getCalificacion()));
        intent.putExtra("valoracion",String.valueOf(peliculas.get(position).getPopularidad()));
        intent.putExtra("fecha",peliculas.get(position).getFecha());
        intent.putExtra("descripcion",peliculas.get(position).getDescripcion());
        intent.putExtra("urlImagen",peliculas.get(position).getUrlImagen());
        startActivity(intent);
    }

}
