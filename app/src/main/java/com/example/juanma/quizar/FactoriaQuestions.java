package com.example.juanma.quizar;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

/**
 * Created by Juanma on 27/04/2015.
 */
public abstract class FactoriaQuestions extends ActionBarActivity {


    FactoriaQuestions(){
    }

    FactoriaQuestions(FactoriaQuestions pregres){
    }

    FactoriaQuestions(String pregun, String resp1, String resp2, String resp3, String resp4, int correct, String img, String sonido) {
    }

    public abstract void añadirPreguntas(String pregunta, String resp1, String resp2, String resp3, String resp4, int correct,String img,String sonido,int id);
    public abstract ArrayList<String> getRespuestas();
    public abstract String getPregunta();
    public abstract String getRespuesta1();
    public abstract String getRespuesta2();
    public abstract String getRespuesta3();
    public abstract String getRespuesta4();
    public abstract int getCorrecta();
    public abstract String getImg();
    public abstract String getSound();
    public abstract int getId();
}
