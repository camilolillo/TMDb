package camilolillo.peliculapp;

public class Pelicula {

    private int votos;
    private int id;
    private double calificacion;
    private String titulo;
    private double popularidad;
    private String urlImagen;
    private String descripcion;
    private String fecha;

    public Pelicula(int votos, int id, double calificacion, String titulo, double popularidad, String urlImagen, String descripcion, String fecha) {
        this.votos = votos;
        this.id = id;
        this.calificacion = calificacion;
        this.titulo = titulo;
        this.popularidad = popularidad;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getVotos() {
        return votos;
    }

    public int getId() {
        return id;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPopularidad() {
        return popularidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPopularidad(double popularidad) {
        this.popularidad = popularidad;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
