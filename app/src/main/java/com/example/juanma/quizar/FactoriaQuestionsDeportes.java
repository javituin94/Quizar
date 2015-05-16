package com.example.juanma.quizar;

import java.util.ArrayList;

/**
 * Created by Juanma on 13/05/2015.
 */
public class FactoriaQuestionsDeportes extends FactoriaQuestions {
    int id_pregunta;
    String pregunta;
    String resp1;
    String resp2;
    String resp3;
    String resp4;
    String img;
    String sound;
    int correcta;

    FactoriaQuestionsDeportes(){
    }

    FactoriaQuestionsDeportes(FactoriaQuestions pregres){
        this.id_pregunta=pregres.getId();
        this.pregunta=pregres.getPregunta();
        this.resp1=pregres.getRespuesta1();
        this.resp2=pregres.getRespuesta2();
        this.resp3=pregres.getRespuesta3();
        this.resp4=pregres.getRespuesta4();
        this.correcta=pregres.getCorrecta();
        this.img=pregres.getImg();
        this.sound=pregres.getSound();
    }

    FactoriaQuestionsDeportes(String pregun, String resp1, String resp2, String resp3, String resp4, int correct, String img, String sonido){
        this.pregunta=pregun;
        this.resp1=resp1;
        this.resp2=resp2;
        this.resp3=resp3;
        this.resp4=resp4;
        this.img=img;
        this.sound=sonido;
        this.correcta=correct;
    }

    public void añadirPreguntas(String pregunta, String resp1, String resp2, String resp3, String resp4, int correct,String img,String sonido,int id){
        this.pregunta=pregunta;
        this.resp1=resp1;
        this.resp2=resp2;
        this.resp3=resp3;
        this.resp4=resp4;
        this.img=img;
        this.sound=sonido;
        this.correcta=correct;
    }

    public ArrayList<String> getRespuestas(){
        ArrayList<String> respuestas=new ArrayList<String>();
        respuestas.add(resp1);
        respuestas.add(resp2);
        respuestas.add(resp3);
        respuestas.add(resp4);
        return respuestas;
    }

    public int getId(){return this.id_pregunta; }
    public String getPregunta() {
        return this.pregunta;
    }
    public String getRespuesta1(){
        return this.resp1;
    }
    public String getRespuesta2(){
        return this.resp2;
    }
    public String getRespuesta3(){
        return this.resp3;
    }
    public String getRespuesta4(){
        return this.resp4;
    }
    public int getCorrecta(){
        return this.correcta;
    }
    public String getImg(){
        return this.img;
    }
    public String getSound(){
        return this.sound;
    }
}
