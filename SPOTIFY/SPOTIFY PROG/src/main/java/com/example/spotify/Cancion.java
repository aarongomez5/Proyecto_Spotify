package com.example.spotify;

public class Cancion {
    private String nombre_cancion;

    public Cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }
}
