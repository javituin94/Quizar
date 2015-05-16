package com.example.juanma.quizar;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Juanma on 11/05/2015.
 */
public class FactoriaDeportes extends Factoria {

    private BD bd;

    FactoriaDeportes(){
    }

    FactoriaDeportes(Context context){
        bd= new BD(context);
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FactoriaQuestions getPregunta(){
        return (bd.extraerPregunta(6));
    }

    public FactoriaQuestions getPregunta(ArrayList id_preguntas){
        return (bd.extraerPregunta(6,id_preguntas));
    }

    public void close(){
        bd.close();
    }

}
