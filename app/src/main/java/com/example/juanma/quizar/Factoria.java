package com.example.juanma.quizar;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

/**
 * Created by Juanma on 11/05/2015.
 */
public abstract class Factoria extends ActionBarActivity {
    public abstract FactoriaQuestions getPregunta();
    public abstract FactoriaQuestions getPregunta(ArrayList id_pregunta);
    public abstract void close();
}
