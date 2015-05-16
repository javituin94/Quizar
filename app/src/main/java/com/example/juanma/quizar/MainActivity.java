package com.example.juanma.quizar;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private Button jugar;
    private Button resultados;
    private Button otros;
    private Button setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.ajustes, false);
        // Instanciar elemento
        jugar = (Button) findViewById(R.id.btn_play);

        // Accion del boton
        jugar.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreguntasActivity.class);
                startActivity(intent);
            }
        });

        resultados = (Button) findViewById(R.id.btn_stats);

        resultados.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResultadosActivity.class);
                startActivity(intent);
            }
        });

        otros = (Button) findViewById(R.id.btn_otherGames);

        otros.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/search?q=preguntas")));
            }
        });

        setting = (Button) findViewById(R.id.btn_settings);

        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AjustesActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            startActivity(new Intent(MainActivity.this,AjustesActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
