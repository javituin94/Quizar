package com.example.juanma.quizar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;




public class PreguntasActivity extends ActionBarActivity {


    private FactoriaQuestions question;
    private TextView textPregunta;
    private TextView textResp1;
    private TextView textResp2;
    private TextView textResp3;
    private TextView textResp4;
    private Button play;
    private ImageView imagen;
    private ImageView imgResp1;
    private ImageView imgResp2;
    private ImageView imgResp3;
    private ImageView imgResp4;
    private int correcta;
    private int puntuacion=0;
    private int categoria=1;
    private int ronda=0;
    private ArrayList id_preguntas;
    private ArrayList<String> respuestas;
    private Factoria factoria;
    private Context context;
    private MediaPlayer reproductor;
    private MediaPlayer resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        //Inicio de variables
        context=this.getBaseContext();
        factoria=new FactoriaCine(context);
        question=new FactoriaQuestionsCine(factoria.getPregunta());
        textPregunta=(TextView) findViewById(R.id.textPregunta);
        textResp1=(TextView) findViewById(R.id.textResp1);
        textResp2=(TextView) findViewById(R.id.textResp2);
        textResp3=(TextView) findViewById(R.id.textResp3);
        textResp4=(TextView) findViewById(R.id.textResp4);
        imgResp1=(ImageView) findViewById(R.id.imgResp1);
        imgResp2=(ImageView) findViewById(R.id.imgResp2);
        imgResp3=(ImageView) findViewById(R.id.imgResp3);
        imgResp4=(ImageView) findViewById(R.id.imgResp4);
        imagen=(ImageView) findViewById(R.id.image);
        play=(Button) findViewById(R.id.btn_play);
        reproductor= new MediaPlayer();
        resp=new MediaPlayer();
        respuestas=new ArrayList<String>();
        id_preguntas=new ArrayList();

        //Asignacion de valores
        correcta=question.getCorrecta();
        respuestas=question.getRespuestas();
        String c = respuestas.get(correcta-1);
        Collections.shuffle(respuestas);
        textPregunta.setText(question.getPregunta());
        textResp1.setText(respuestas.get(0));
        textResp2.setText(respuestas.get(1));
        textResp3.setText(respuestas.get(2));
        textResp4.setText(respuestas.get(3));
        puntuacion=0;
        correcta=respuestas.indexOf(c)+1;

        if(!question.getImg().equals("NULL")){
            imagen.getLayoutParams().height = 200;
            imagen.getLayoutParams().width = 200;
            imagen.setImageResource(getResources().getIdentifier(question.getImg(), "drawable", getPackageName()));
            imagen.setVisibility(View.VISIBLE);
            imagen.requestLayout();
        }else {
            imagen.getLayoutParams().height = 0;
            imagen.getLayoutParams().width = 0;
            imagen.setImageResource(0);
            imagen.requestLayout();

        }

        if(!question.getSound().equals("NULL")){
            play.getLayoutParams().height = 100;
            play.getLayoutParams().width = 100;
            play.requestLayout();
            play.setVisibility(View.VISIBLE);
            reproductor=MediaPlayer.create(context, getResources().getIdentifier(question.getSound(), "raw", getPackageName()));
            reproductor.setLooping(true);
        }else {
            play.getLayoutParams().height = 0;
            play.getLayoutParams().width = 0;
            play.requestLayout();
        }

        //Eventos Clicks
        textResp1.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                if(correcta==1){
                    imgResp1.setBackgroundResource(getResources().getIdentifier("correct","drawable",getPackageName()));
                    resp=MediaPlayer.create(context, R.raw.correct);
                    resp.start();
                    puntuacion+=100;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.VISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.INVISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.VISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },600);
                }else{
                    resp=MediaPlayer.create(context, R.raw.wrong);
                    resp.start();
                    imgResp1.setBackgroundResource(getResources().getIdentifier("wrong","drawable",getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.VISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.INVISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp1.setVisibility(View.VISIBLE);
                            textResp1.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },600);
                }
                siguientePregunta();
            }
        });

        textResp2.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                if(correcta==2){
                    resp=MediaPlayer.create(context, R.raw.correct);
                    resp.start();
                    imgResp2.setBackgroundResource(getResources().getIdentifier("correct","drawable",getPackageName()));
                    puntuacion+=100;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.VISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.INVISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.VISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },600);
                }else{
                    resp=MediaPlayer.create(context, R.raw.wrong);
                    resp.start();
                    imgResp2.setBackgroundResource(getResources().getIdentifier("wrong","drawable",getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.VISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.INVISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp2.setVisibility(View.VISIBLE);
                            textResp2.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },600);
                }
                siguientePregunta();

            }
        });

        textResp3.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                if(correcta==3){
                    puntuacion+=100;
                    resp=MediaPlayer.create(context, R.raw.correct);
                    resp.start();
                    imgResp3.setBackgroundResource(getResources().getIdentifier("correct", "drawable", getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.VISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.INVISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.VISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },600);
                }else{
                    resp=MediaPlayer.create(context, R.raw.wrong);
                    resp.start();
                    imgResp3.setBackgroundResource(getResources().getIdentifier("wrong", "drawable", getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.VISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.INVISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp3.setVisibility(View.VISIBLE);
                            textResp3.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },600);
                }
                siguientePregunta();

            }
        });

        textResp4.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                if(correcta==4){
                    puntuacion+=100;
                    resp=MediaPlayer.create(context, R.raw.correct);
                    resp.start();
                    imgResp4.setBackgroundResource(getResources().getIdentifier("correct","drawable",getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.VISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.INVISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.VISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.PaleGreen));
                        }
                    },600);
                }else{
                    resp=MediaPlayer.create(context, R.raw.wrong);
                    resp.start();
                    imgResp4.setBackgroundResource(getResources().getIdentifier("wrong","drawable",getPackageName()));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.VISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },250);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.INVISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.azulCielo));
                        }
                    },400);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgResp4.setVisibility(View.VISIBLE);
                            textResp4.setBackgroundColor(getResources().getColor(R.color.Crimson));
                        }
                    },600);
                }
                siguientePregunta();

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reproductor.isPlaying()){
                    play.setBackgroundResource(getResources().getIdentifier("pause", "drawable", getPackageName()));
                    play.requestLayout();
                    reproductor.pause();
                }else{
                    play.setBackgroundResource(getResources().getIdentifier("play", "drawable", getPackageName()));
                    play.requestLayout();
                    reproductor.start();
                }
            }
        });

        factoria.close();
    }

    public void onDestroy(){
        super.onDestroy();
        if(reproductor.isPlaying())
            reproductor.stop();
        reproductor.release();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preguntas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void siguientePregunta() {
            categoria = (categoria % 6) + 1;

            if(categoria==1){
                ronda++;
                this.siguientePregunta();
            }else {
                if (ronda == 3) {
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (resp.isPlaying())
                                resp.stop();
                            if (reproductor.isPlaying())
                                resp.stop();
                            Intent intent = new Intent(PreguntasActivity.this, ResultadoActivity.class);
                            intent.putExtra("puntuacion", puntuacion);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);
                } else {
                    textResp1.setEnabled(false);
                    textResp2.setEnabled(false);
                    textResp3.setEnabled(false);
                    textResp4.setEnabled(false);


                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            limpiar();
                            switch (categoria) {
                                case 1:
                                    factoria = new FactoriaCine(context);
                                    question = new FactoriaQuestionsCine(factoria.getPregunta(id_preguntas));
                                    break;
                                case 2:
                                    factoria = new FactoriaMusica(context);
                                    question = new FactoriaQuestionsMusica(factoria.getPregunta(id_preguntas));
                                    break;
                                case 3:
                                    factoria = new FactoriaHistoria(context);
                                    question = new FactoriaQuestionsHistoria(factoria.getPregunta(id_preguntas));
                                    break;
                                case 4:
                                    factoria = new FactoriaJuegos(context);
                                    question = new FactoriaQuestionsJuegos(factoria.getPregunta(id_preguntas));
                                    break;
                                case 5:
                                    factoria = new FactoriaSeries(context);
                                    question = new FactoriaQuestionsSeries(factoria.getPregunta(id_preguntas));
                                    break;
                                case 6:
                                    factoria = new FactoriaDeportes(context);
                                    question = new FactoriaQuestionsDeportes(factoria.getPregunta(id_preguntas));
                                    break;
                            }
                            id_preguntas.add(question.getId());
                            factoria.close();
                            correcta = question.getCorrecta();
                            respuestas = new ArrayList<String>();
                            respuestas = question.getRespuestas();
                            String c = respuestas.get(correcta - 1);
                            Collections.shuffle(respuestas);
                            textPregunta.setText(question.getPregunta());
                            textResp1.setText(respuestas.get(0));
                            textResp2.setText(respuestas.get(1));
                            textResp3.setText(respuestas.get(2));
                            textResp4.setText(respuestas.get(3));
                            correcta = respuestas.indexOf(c) + 1;

                            if (!question.getImg().equals("NULL")) {
                                imagen.getLayoutParams().height = 200;
                                imagen.getLayoutParams().width = 300;
                                imagen.setVisibility(View.VISIBLE);
                                imagen.setImageResource(getResources().getIdentifier(question.getImg(), "drawable", getPackageName()));
                                imagen.requestLayout();
                                System.out.println(question.getImg() + " " + getPackageName());
                                System.out.println("Imagen: " + getResources().getIdentifier(question.getImg(), "drawable", getPackageName()));

                            }

                            if (!question.getSound().equals("NULL")) {
                                play.getLayoutParams().height = 100;
                                play.getLayoutParams().width = 100;
                                play.setVisibility(View.VISIBLE);
                                play.requestLayout();
                                reproductor = MediaPlayer.create(context, getResources().getIdentifier(question.getSound(), "raw", getPackageName()));
                                reproductor.setLooping(true);
                            }

                        }
                    }, 1000);
                }
            }

    }



    public void limpiar(){
        textResp1.setEnabled(true);
        textResp2.setEnabled(true);
        textResp3.setEnabled(true);
        textResp4.setEnabled(true);
        imagen.setVisibility(View.INVISIBLE);
        imgResp1.setVisibility(View.INVISIBLE);
        imgResp2.setVisibility(View.INVISIBLE);
        imgResp3.setVisibility(View.INVISIBLE);
        imgResp4.setVisibility(View.INVISIBLE);
        imagen.setImageResource(0);
        imagen.getLayoutParams().height = 0;
        imagen.getLayoutParams().width = 0;
        imagen.requestLayout();
        play.getLayoutParams().height = 0;
        play.getLayoutParams().width = 0;
        play.requestLayout();
        play.setVisibility(View.INVISIBLE);
        if(resp.isPlaying())
            resp.stop();
        if(reproductor.isPlaying())
            reproductor.stop();
        textResp1.setBackgroundColor(getResources().getColor(R.color.azulCielo));
        textResp2.setBackgroundColor(getResources().getColor(R.color.azulCielo));
        textResp3.setBackgroundColor(getResources().getColor(R.color.azulCielo));
        textResp4.setBackgroundColor(getResources().getColor(R.color.azulCielo));
    }
    /*
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("categoria",categoria);
        outState.putString("pregunta", question.getPregunta());
        outState.putString("resp1",question.getRespuesta1());
        outState.putString("resp2",question.getRespuesta2());
        outState.putString("resp3",question.getRespuesta3());
        outState.putString("resp4",question.getRespuesta4());
        outState.putInt("correcta",question.getCorrecta());
        outState.putInt("id",question.getId());
        outState.putString("img",question.getImg());
        outState.putString("sound",question.getSound());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        categoria = savedInstanceState.getInt("categoria");
        correcta = savedInstanceState.getInt("correcta");
        textPregunta.setText(savedInstanceState.getString("pregunta"));
        textResp1.setText(savedInstanceState.getString("resp1"));
        textResp2.setText(savedInstanceState.getString("resp2"));
        textResp3.setText(savedInstanceState.getString("resp3"));
        textResp4.setText(savedInstanceState.getString("resp4"));
        if(!savedInstanceState.getString("img").equals("NULL"))
            imagen.setImageResource(getResources().getIdentifier(savedInstanceState.getString("img"),"drawable",getPackageName()));
        if(!savedInstanceState.getString("sound").equals("NULL"))
            reproductor=MediaPlayer.create(context, getResources().getIdentifier(savedInstanceState.getString("sound"), "raw", getPackageName()));
    }
    */
}
