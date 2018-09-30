package camilolillo.peliculapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private Context contexto;
    private ArrayList<Pelicula> peliculas;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public PeliculaAdapter(Context contexto, ArrayList<Pelicula> peliculas) {
        this.contexto = contexto;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(contexto).inflate(R.layout.previsualizacion_pelicula,parent,false);
        return new PeliculaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w200";
        Pelicula pelicula = peliculas.get(position);
        holder.titulo.setText(pelicula.getTitulo());
        holder.evaluacion.setText(Double.toString(pelicula.getCalificacion()));
        Picasso.get().load(url+pelicula.getUrlImagen()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo;
        public TextView evaluacion;
        public ImageView imagen;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.preTitulo);
            evaluacion = itemView.findViewById(R.id.preEvaluacion);
            imagen = itemView.findViewById(R.id.preImagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
