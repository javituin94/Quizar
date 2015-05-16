package com.example.juanma.quizar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


public class ResultadoActivity extends ActionBarActivity {
    private BD bd;
    private TextView resultado;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        resultado=(TextView) findViewById(R.id.result);
        int puntos=getIntent().getIntExtra("puntuacion",0);
        resultado.setText(Integer.toString(puntos));
        introducirResultado(puntos);
        boton = (Button) findViewById(R.id.btn_back1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               boton.setEnabled(true);
            }
        }, 1500);


    }

    private void introducirResultado(int puntos) {
        bd=new BD(this.getApplicationContext());
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bd.introducirResultados(puntos);
        bd.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultado, menu);
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
}
