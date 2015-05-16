package com.example.juanma.quizar;

/**
 * Created by Juanma on 13/05/2015.
 */
public class Resultados {
    protected long id;
    protected int puntos;

    public Resultados() {
        this.puntos = 0;

    }

    public Resultados(long id, int puntos) {
        this.id=id;
        this.puntos = puntos;
    }

    public Resultados(int puntos) {
        this.puntos = puntos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


}
