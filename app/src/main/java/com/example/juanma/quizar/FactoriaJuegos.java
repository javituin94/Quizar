package com.example.juanma.quizar;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Juanma on 11/05/2015.
 */
public class FactoriaJuegos extends Factoria {

    private BD bd;

    FactoriaJuegos(){
    }

    FactoriaJuegos(Context context){
        bd= new BD(context);
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FactoriaQuestions getPregunta(){
        return (bd.extraerPregunta(4));
    }

    public FactoriaQuestions getPregunta(ArrayList id_preguntas){
        return (bd.extraerPregunta(4,id_preguntas));
    }

    public void close(){
        bd.close();
    }

}
